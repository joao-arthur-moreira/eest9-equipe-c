package domain;

import entities.Context;
import entities.annotations.ActionDescriptor;
import entities.annotations.Param;
import entities.annotations.ParameterDescriptor;
import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import service.DoubleToStr;

@Entity
@SequenceGenerator(name = "GEN_PEDIDO_VENDA",sequenceName = "GEN_PEDIDO_VENDA")
@NamedQueries({
    @NamedQuery(
            name = "buscaPedidosPorCliente",
            query = "Select u"
            + " From Produto u"
            + " Where u.nome Like :pesquisa"
            + " Or u.codigo = :codigo"
    )
})
@Views(
        {
            @View(
                    hidden = true,
                    name = "listaPedidosCliente",
                    title = "Listagem de pedidos do cliente '#{currentCliente}'",
                    filters = "data,status",
                    members
                    = "id,cliente.nome,data,qtdItens,status,total,usuario.userName,contaReceber.valorFalta,"
                            + "'Ações'[executarPagamento(),exibirDetalhes(),cancelar()]",
                    namedQuery = "From PedidoVenda p Where p.cliente = :cliente Order by p.id ",
                    params = {
                        @Param(name = "cliente", value = "#{currentCliente}")},
                    rows = 10,
                    template = "@PAGER+@CRUD+@FILTER",
                    roles = "Admin,Vendedor"
            )
            ,
            @View(
                    name = "listaPedidos",
                    title = "Listagem de pedidos",
                    filters = "data,cliente,status",
                    members
                    = "id,cliente.nome,data,qtdItens,status,total,usuario.userName,contaReceber.valorFalta,"
                            + "'Ações'[exibirDetalhes(),cancelar()]",
                    namedQuery = "From PedidoVenda p  Order by p.id ",
                    rows = 10,
                    template = "@PAGER+@FILTER",
                    roles = "Admin,Vendedor"
            )
            ,
            @View(
                    name = "detalhePedido",
                    hidden = true,
                    title = "Detalhe do pedido",
                    namedQuery = "From PedidoVenda p Where p.id = :id",
                    params = {
                        @Param(name = "id", value = "#{ID_PEDIDO_VENDA}")},
                    members = "["
                            + " [novoPedido(),listarPedidos(),executarPagamento(), cancelar()];"
                            + " Pedido["
                            + "         [id,cliente.nome,qtdItens,total];"
                            + "         [status,usuario.userName];"
                            + "];"
                            + " 'Dados cliente'-["
                            + "     *cliente.credito.utilizado, *cliente.credito.limite, *cliente.credito.disponivel;"
                            + "     *cliente.enderecoCliente.endereco, *cliente.enderecoCliente.numero, "
                            + "     *cliente.enderecoCliente.bairro; *cliente.enderecoCliente.cidade,*cliente.enderecoCliente.uf,"
                            + "     *cliente.enderecoCliente.cep;"
                            + "     *cliente.contato.telefone1,*cliente.contato.telefone1WhatsApp,*cliente.contato.telefone2,*cliente.contato.telefone2WhatsApp;"
                            + "     *cliente.contato.email"
                            + " ];"
                            + " 'Itens'+[itens<produto.codigo,produto.nome,qtd,unitario,total>];"
                            + " Financeiro-["
                            + "             [*contaReceber.valorPago,*contaReceber.valorFalta];"
                            + "             contaReceber.pagamentos<dataHora,usuario,valor>"
                            + "        ]"
                            + "]",
                    roles = "Admin,Vendedor"
            )
            ,
            @View(
                    name = "novoPedidoVenda",
                    title = "Cadastrar pedido",
                    namedQuery = "Select new domain.PedidoVenda()",
                    members = "["
                    + "' '[#cliente;*id,#data;];"
                    + "' '[Itens[adicionaItem();"
                    + "itens<produto.nome,qtd,unitario,total,apagar(),editar()>]"
                            + "];"
                    + "[registrar(),cancelar()];"
                    + "[qtdItens,total]"
                    + "]",
                    roles = "Admin,Vendedor"
            //                    template = ""
            )
        }
)
public class PedidoVenda implements Serializable {

    @Id
    @GeneratedValue (generator = "GEN_PEDIDO_VENDA",strategy = GenerationType.AUTO)
    @PropertyDescriptor(displayName = "Número")
    private Long id;

    @ManyToOne
    @NotNull
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL)
    private ContaReceber contaReceber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido",
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<PedidoVendaItem> itens;

    @NotNull
    private Date data;

    @Column(precision = 4)
    private int qtdItens;

    @Column(precision = 4, scale = 2)
    private double total;

    @Column(nullable = false)
    private StatusPedidoVenda status;

    @Version
    private Long dataCadastro;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public PedidoVenda() {
        cliente = new Cliente();
        itens = new ArrayList<PedidoVendaItem>();
        data = new Date();
        status = StatusPedidoVenda.Novo;
        contaReceber = new ContaReceber();
        usuario = (Usuario) Context.getCurrentUser();
    }

    private boolean produtoNoPedido(Produto produto) {
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getProduto().getId().equals(produto.getId())) {
                return true;
            }
        }
        return false;
    }

    public String adicionaItem(
            @ParameterDescriptor(displayName = "Selecione o produto", required = true) Produto produto,
            @ParameterDescriptor(displayName = "Quantidade", required = true, defaultValue = "1.00") double qtd) {
        // Não permite venda com quantidade zerada
        if (qtd == 0) {
            throw new IllegalStateException("A quantidade precisa ser maior do que zero!");
        }
        // Não permite venda com preço zerado
        if (produto.getPreco() <= 0) {
            throw new IllegalStateException("Não pode incluir produto com preço zero!");
        }
        // Não permite duplicar item na venda
        if (produtoNoPedido(produto)) {
            throw new IllegalStateException("O produto já consta no pedido!");
        }

        double total_item = DoubleToStr.twoPlaces(qtd * produto.getPreco());
        double total_pedido = DoubleToStr.twoPlaces( total + total_item);

        if (total_pedido > 1000) {
            throw new IllegalStateException("O pedido ultrapassa o valor de R$ 1.000,00!");
        }

        PedidoVendaItem novo = new PedidoVendaItem();
        novo.setPedido(this);
        novo.setProduto(produto);
        novo.setQtd(qtd);
        novo.setTotal(total_item);
        itens.add(novo);
        // Atualizando o total da venda
        qtdItens++;
        total = total_pedido;
        return "Item adicionado com êxito.";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ContaReceber getContaReceber() {
        return contaReceber;
    }

    public void setContaReceber(ContaReceber contaReceber) {
        this.contaReceber = contaReceber;
    }

    public List<PedidoVendaItem> getItens() {
        return itens;
    }

    public void setItens(List<PedidoVendaItem> itens) {
        this.itens = itens;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(int qtdItens) {
        this.qtdItens = qtdItens;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = DoubleToStr.twoPlaces(total);
    }

    public StatusPedidoVenda getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoVenda status) {
        this.status = status;
    }

    public Long getDataCadastro() {
        return dataCadastro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PedidoVenda other = (PedidoVenda) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @ActionDescriptor(
            confirm = true,
            confirmMessage = "Deseja realmente persistir as alterações?"
    )
    public String registrar() {
        return this.status.gravarNovo(this);
    }

    @ActionDescriptor(
            confirm = true,
            confirmMessage = "ATENÇÃO. Essa operação é irreversível! Deseja realmente cancelar o pedido selecionado?"
            
    )
    public String cancelar() {
        return this.status.cancelar(this,(Usuario) Context.getCurrentUser());
    }

    public String exibirDetalhes() {
        Context.setValue("ID_PEDIDO_VENDA", this.id);
        return "go:domain.PedidoVenda@detalhePedido";
    }
    
    public String listarPedidos(){
        return "go:domain.PedidoVenda@listaPedidos";
    }
    
    public String novoPedido(){
        return "go:domain.PedidoVenda@novoPedidoVenda";
    }

    public String executarPagamento(
            @ParameterDescriptor(displayName = "Valor a ser pago", required = true) double valor) {
        return this.status.pagar(this, valor,(Usuario) Context.getCurrentUser());
    }

    @Override
    public String toString() {
        return id.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}

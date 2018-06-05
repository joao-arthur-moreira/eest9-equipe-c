/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import entities.Repository;
import entities.annotations.ActionDescriptor;
import entities.annotations.Param;
import entities.annotations.ParameterDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.NotImplementedException;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "buscaPedidosPorCliente",
            query = "Select u"
            +       " From Produto u"
            +       " Where u.nome Like :pesquisa"
            +       " Or u.codigo = :codigo"
    )
})
@Views(
        {
            @View(
                    name = "listaPedidosCliente",
                    title = "Listagem de pedidos por cliente",
//                    filters = "nome,codigo",
                    members = "id,cliente.nome,data,qtdItens,status,total",
                    namedQuery = "From PedidoVenda p Where p.cliente = :cliente Order by p.id ",
                    params = {@Param(name="cliente",value="#{Context.currentCliente}")},
                    rows = 10,
                    template = "@PAGE+@CRUD+@FILTER"
            ),
            @View(
                    name = "novoPedidoVenda",
                    title = "Cadastrar pedido",
//                    filters = "nome,codigo",
                    namedQuery = "Select new domain.PedidoVenda()",
                    members = "["
                            + "[#cliente;*id,#data;];"
                            + "Itens[adicionaItem();"
                            + "itens<produto.nome,qtd,total,apagar(),editar()>];"
                            + "[registrar(),cancelar()];"
                            + "[qtdItens,total]"
                            + "]"
                                      
//                    template = ""
            )
        }
)
public class PedidoVenda implements Serializable {
    @Id 
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Cliente cliente;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ContaReceber contaReceber;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<PedidoVendaItem> itens;
    
    @NotNull
    private Date data;
    
    @Column(precision = 4)
    private int qtdItens;
    
    @Column(precision =4, scale = 2)
    private double total;
    
    @Column(nullable = false)
    private StatusPedidoVenda status;

    public PedidoVenda() {
        cliente = new Cliente();
        itens = new ArrayList<PedidoVendaItem>();
        data = new Date();
        status = StatusPedidoVenda.Novo;
        contaReceber = new ContaReceber();
    }
    
    private boolean produtoNoPedido(Produto produto){
        for(int i = 0; i < itens.size() ; i++ ){
            if ( itens.get(i).getProduto().getId().equals(produto.getId()) ){
                return true;
            }
        }
        return false;
    }
    
    public String adicionaItem(
            @ParameterDescriptor(displayName = "Selecione o produto", required = true)
                    Produto produto, 
            @ParameterDescriptor(displayName = "Quantidade", required = true) 
                    double qtd){
        // Não permite venda com quantidade zerada
        if ( qtd == 0 ){
            throw new IllegalStateException("A quantidade precisa ser maior do que zero!");
        }
        // Não permite venda com preço zerado
        if (produto.getPreco() <= 0 ){
            throw new IllegalStateException("Não pode incluir produto com preço zero!");
        }
        // Não permite duplicar item na venda
        if ( produtoNoPedido(produto)){
            throw new IllegalStateException("O produto já consta no pedido!");
        }
        
        PedidoVendaItem novo = new PedidoVendaItem();
        novo.setPedido(this);
        novo.setProduto(produto);
        novo.setQtd(qtd);
        novo.setTotal(qtd * produto.getPreco());
        itens.add(novo);
        // Atualizando o total da venda
        qtdItens++;
        total += novo.getTotal();
        return "Item adicionado com êxisto.";
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
        this.total = total;
    }

    public StatusPedidoVenda getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoVenda status) {
        this.status = status;
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
    public String registrar(){
        return this.status.gravarNovo(this);
    }
    
    @ActionDescriptor(
             confirm = true,
            confirmMessage = "Deseja realmente cancelar?"
    )
    public String cancelar(){
        return this.status.cancelar(this);
    }
    
}

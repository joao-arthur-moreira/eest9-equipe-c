package domain;

import entities.Context;
import entities.Repository;
import entities.annotations.ActionDescriptor;
import entities.annotations.Param;
import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.NotImplementedException;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@SequenceGenerator(name="GEN_CLIENTE",sequenceName = "GEN_CLIENTE")
@NamedQueries(
        {@NamedQuery(
                name = "qtdClientePorCnpj",
                query = "Select count(c) from Cliente c Where c.cnpj = :cnpj and c.id <> :id"
        )}
)
@Views(
        {
            @View(
                    name = "CadastroCliente",
                    title = "Listagem de Clientes",
                    filters = "nome,cnpj,enderecoCliente.cidade",
                    header = "",
                    members = "*id,nome,cnpj,credito.limite,*credito.utilizado,*credito.disponivel,*enderecoCliente.cidade,'Ações'[listarPedidosDoCliente(),editar(),excluir()]",
                    namedQuery = "From Cliente p Order by p.nome ",
                    rows = 10,
                    template = "@PAGER+@FILTER"
            )
            ,
            @View(
                    hidden = true,
                    name = "EditarCliente",
                    title = "Cadastro do cliente",
                    header = "Ctrl.DAO.editPage(),Ctrl.DAO.savePage(),Ctrl.DAO.cancelPage()",
                    members = "["
                    + "'Métodos'[incluir(),listarClientes()];"
                    + "[*id,nome,cnpj];"
                    + "'Crédito'[credito.limite,*credito.utilizado,*credito.disponivel];"
                    + "'Localização'["
                            + "enderecoCliente.endereco,enderecoCliente.numero;"
                            + "enderecoCliente.bairro,enderecoCliente.cidade;"
                            + "enderecoCliente.uf,enderecoCliente.cep"
                            + "];"
                    + "'Contato'["
                            + "contato.telefone1, contato.telefone1WhatsApp;"
                            + "contato.telefone2, contato.telefone2WhatsApp;"
                            + "contato.email"
                            + "]"
                            
                    + "]",
                    namedQuery = "Select c From Cliente c Where c = :cliente ",
                    params = {
                        @Param(name = "cliente", value = "#{CLIENTE_SELECIONADO}")},
                    template = ""
            )
            ,
            @View(
                    hidden = false,
                    name = "InserirCliente",
                    title = "Cadastrar novo cliente",
                    namedQuery = "Select new domain.Cliente()",
                    header = "",
                    members = "["
                    + "'Métodos'[salvarCliente(),listarClientes()];"
                    + "[*id,#nome,#cnpj];"
                    + "'Crédito'[#credito.limite,*credito.utilizado,*credito.disponivel];"
                    + "'Localização'["
                            + "#enderecoCliente.endereco,#enderecoCliente.numero;"
                            + "#enderecoCliente.bairro,#enderecoCliente.cidade;"
                            + "#enderecoCliente.uf,#enderecoCliente.cep"
                            + "];"
                    + "'Contato'["
                            + "#contato.telefone1, #contato.telefone1WhatsApp;"
                            + "#contato.telefone2, #contato.telefone2WhatsApp;"
                            + "#contato.email"
                            + "]"
                    + "]",
                    template = ""
            )
        }
)
public class Cliente implements Serializable {

    @Id
    @GeneratedValue (generator = "GEN_CLIENTE",strategy = GenerationType.AUTO)
    @PropertyDescriptor(displayName = "Número")
    private Long id;

    @PropertyDescriptor(displayWidth = 50)
    @Column(nullable = false, length = 100)
    @NotEmpty
    @NotNull
    private String nome;

    // 08.574.018-0001/09
    @Column(nullable = false, length = 18, unique = true)
    @NotEmpty
    @NotNull
    @CNPJ()
    private String cnpj;

    @OneToMany(mappedBy = "cliente")
    private List<PedidoVenda> pedidos;

    @Embedded
    private Credito credito;

    @Embedded
    private EnderecoCliente enderecoCliente;
    
    @Embedded
    private ContatoCliente contato;

    @OneToMany(mappedBy = "cliente")
    private List<ContaReceber> contasReceber;

    public Cliente() {
        enderecoCliente = new EnderecoCliente();
        credito = new Credito();
        contato = new ContatoCliente();
    }

    public static List<Cliente> buscaClientes(String pesquisa) {
        throw new NotImplementedException();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PedidoVenda> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoVenda> pedidos) {
        this.pedidos = pedidos;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public List<ContaReceber> getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(List<ContaReceber> contasReceber) {
        this.contasReceber = contasReceber;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Cliente other = (Cliente) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String listarPedidosDoCliente() {
        Context.setValue("currentCliente", this);
        return "go:domain.PedidoVenda@listaPedidosCliente";
    }

    @Override
    public String toString() {
        return nome;
    }

    public EnderecoCliente getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(EnderecoCliente enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public ContatoCliente getContato() {
        return contato;
    }

    public void setContato(ContatoCliente contato) {
        this.contato = contato;
    }
    
    

    public String salvarCliente() {
        // Verifica se já existe um cliente com o cnpj 
        Long qtd = Repository.queryUnique("qtdClientePorCnpj", this.cnpj, this.id!=null?this.id:0);
        if ( qtd > 0 ){
            throw new IllegalStateException("Já existe um cliente cadastrado com o cnpj \""+this.cnpj +"\".");
        }
        // Salvando
        Repository.save(this);
        Context.setValue("CLIENTE_SELECIONADO", this);
        // Redirecionar para tela de edição
        return "go:domain.Cliente@EditarCliente";

    }

    public String editar() {
        // Salva o id do cliente no context 
        Context.setValue("CLIENTE_SELECIONADO", this);
        // Redireciona a view para a tela de edição
        return "go:domain.Cliente@EditarCliente";
    }

    public String incluir() {
        // Redireciona a view para a tela de inclusão
        return "go:domain.Cliente@InserirCliente";
    }

    @ActionDescriptor(
            confirm = true,
            confirmMessage = "ATENÇÃO. Essa operação é irreversível. Deseja realmente excluír o item selecionado?"
    )
    public String excluir() {
        Repository.delete(this);
        return "Registro excluído com êxito.";
    }

    @ActionDescriptor(
            preValidate = false
    )
    public String listarClientes() {
        return "go:domain.Cliente@CadastroCliente";
    }

}

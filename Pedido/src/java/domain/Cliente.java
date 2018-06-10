/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import entities.Context;
import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.apache.commons.lang.NotImplementedException;

@Entity
@Views(
        {
            @View(
                    name = "CadastroCliente",
                    title = "Cadastro de Clientes",
                    filters = "nome,cnpj,endereco.cidade",
                    members = "*id,nome,cnpj,credito.limite,*credito.utilizado,*credito.disponivel,*endereco.cidade,listarPedidosDoCliente(),contasReceber",
                    namedQuery = "From Cliente p Order by p.nome ",
                    rows = 10,
                    template = "@PAGE+@CRUD+@FILTER"
            )
        }
)
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    @PropertyDescriptor(displayName = "Número")
    private Long id;

    @PropertyDescriptor(displayWidth = 50)
    @Column(nullable = false, length = 100)
    private String nome;
    // 08.574.018-0001/09
    @Column(nullable = false, length = 18)
    private String cnpj;

    @OneToMany(mappedBy = "cliente")
    private List<PedidoVenda> pedidos;

    @Embedded
    private Credito credito;

    @Embedded
    private EnderecoCliente endereco;

    @OneToMany(mappedBy = "cliente")
    private List<ContaReceber> contasReceber;

    public Cliente() {
        credito = new Credito();
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

    public EnderecoCliente getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoCliente endereco) {
        this.endereco = endereco;
    }
    
    

}

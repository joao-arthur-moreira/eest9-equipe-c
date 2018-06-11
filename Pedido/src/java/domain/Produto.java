/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import entities.Repository;
import entities.annotations.PropertyDescriptor;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "BuscaProduto",
            query = "Select u"
            +       " From Produto u"
            +       " Where u.nome Like :pesquisa"
            +       " Or u.codigo = :codigo"
    )
})
@Views(
        {
            @View(
                    name = "CadastroProduto",
                    title = "Cadastro de produtos",
                    filters = "nome,codigo",
                    members = "nome,codigo,preco",
                    namedQuery = "From Produto p Order by p.nome ",
                    rows = 10,
                    template = "@PAGER+@CRUD+@FILTER"
            )
        }
)
public class Produto implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank
    @Column(length = 13, unique = true)
    private String codigo;

    @NotNull
    @NotBlank
    @Column(length = 255)
    @PropertyDescriptor(displayWidth = 50)
    private String nome;

    @NotNull
    @Column(precision = 5, scale = 2)
    private Double preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Produto other = (Produto) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    

    public static List<Produto> buscaProduto(String pesquisa) {
        List<Produto> produtos = Repository.query("BuscaProduto ", "%"+pesquisa+"%",pesquisa);
        return produtos;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import entities.annotations.ParameterDescriptor;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.apache.commons.lang.NotImplementedException;

@Entity
public class PedidoVendaItem implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private PedidoVenda pedido;

    @ManyToOne
    private Produto produto;

    @Column(precision = 4, scale = 3)
    private double qtd;

    @Column(precision = 4, scale = 2)
    private double total;

    public PedidoVendaItem(PedidoVenda _pedido) {
        pedido = _pedido;
        
    }
    
    

    public String apagar() {
        throw new NotImplementedException();
    }

    public String editar(
            @ParameterDescriptor(displayName = "Selecione o produto",
                    required = true) Produto produto, 
            @ParameterDescriptor(displayName = "Informe a nova quantidade" ) double qtd) {
        throw new NotImplementedException();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoVenda getPedido() {
        return pedido;
    }

    public void setPedido(PedidoVenda pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQtd() {
        return qtd;
    }

    public void setQtd(double qtd) {
        this.qtd = qtd;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final PedidoVendaItem other = (PedidoVendaItem) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}

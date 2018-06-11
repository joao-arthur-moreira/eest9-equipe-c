/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import service.DoubleToStr;

@Entity
@Views(
        {
            @View (
                    hidden = true,
                    title = "Contas a receber",
                    name = "contaReceber",
                    members = ""
            )
        }
)
public class ContaReceber implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private Cliente cliente;
    
    @OneToOne
    private PedidoVenda pedido;
    
    @Column(precision = 4, scale = 2, nullable = false)
    private double valor;
    
    @Column(precision = 4, scale = 2, nullable = false)
    private double valorPago;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pagamentos> pagamentos;
    
    @Transient
    private double valorFalta;
    
    public double getValorFalta(){
        return valor - valorPago;
    }

    public ContaReceber() {
        pagamentos = new ArrayList<Pagamentos>();
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

    public PedidoVenda getPedido() {
        return pedido;
    }

    public void setPedido(PedidoVenda pedido) {
        this.pedido = pedido;
    }

    public double  getValor() {
        return valor;
    }

    public void setValor(double  valor) {
        this.valor = DoubleToStr.twoPlaces(valor);
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final ContaReceber other = (ContaReceber) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public List<Pagamentos> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamentos> pagamentos) {
        this.pagamentos = pagamentos;
    }
    
    
    
    
}

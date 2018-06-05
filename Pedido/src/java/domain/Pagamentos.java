/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.apache.commons.lang.NotImplementedException;

@Entity
public class Pagamentos implements Serializable {
    @Id 
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private ContaReceber contaReceber;
    
    
    @Column( precision = 4, scale =2)
    private double valor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContaReceber getContaReceber() {
        return contaReceber;
    }

    public void setContaReceber(ContaReceber contaReceber) {
        this.contaReceber = contaReceber;
    }

//    public Timestamp getDataCadastro() {
//        return dataCadastro;
//    }
//
//    public void setDataCadastro(Timestamp dataCadastro) {
//        this.dataCadastro = dataCadastro;
//    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Pagamentos other = (Pagamentos) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    public String excluir(){
         throw  new NotImplementedException();
    }
}

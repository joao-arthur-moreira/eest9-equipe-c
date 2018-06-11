/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.NotImplementedException;

@Entity
@Views(
        {
            @View(
                    hidden = true,
                    title = "Cadastro de pagamentos",
                    name = "cadPagamentos",
                    members = ""
            )}
)
public class Pagamentos implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ContaReceber contaReceber;

    @Column(precision = 4, scale = 2)
    private double valor;

   @Temporal(TemporalType.TIMESTAMP)
    private Date dataHora;

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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
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

    public String excluir() {
        throw new NotImplementedException();
    }
}

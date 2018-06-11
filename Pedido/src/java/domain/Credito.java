/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.NotImplementedException;
import service.DoubleToStr;

@Embeddable
public class Credito implements Serializable {

    @Column(precision = 4, scale = 2, nullable = false)
    @NotNull
    private double limite;

    @Column(precision = 4, scale = 2, nullable = false)
    private double utilizado;
    
    @Transient
    private double disponivel;

    public boolean limiteDisponivel(double valor) {
        throw new NotImplementedException();
    }

    public Credito() {
        limite = 0;
        utilizado = 0;
    }
    
    

    public double valorFalta() {
        return limite - utilizado;
    }

    public void adicionaSaldo(Double valor) {
        this.utilizado+= valor;
        this.utilizado = DoubleToStr.twoPlaces(this.utilizado);
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = DoubleToStr.twoPlaces(limite);
    }

    public double getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(double utilizado) {
        this.utilizado = DoubleToStr.twoPlaces(utilizado);
    }
    
    public double getDisponivel(){
        return limite - utilizado;
    }
    
    
}

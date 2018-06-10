package domain;

import entities.annotations.PropertyDescriptor;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnderecoCliente implements Serializable {
    @Column ( length = 100)
    @PropertyDescriptor(displayWidth = 50)
    private String endereco;
    
    @Column 
    private int numero;
    
    @PropertyDescriptor(displayWidth = 30)
    @Column( length = 100 )
    private String bairro;
    
    @PropertyDescriptor(displayWidth = 20)
    @Column ( length = 50 )
    private String cidade;
    
    @Column ( length = 2 )
    private String uf;
    
    @Column ( length = 8)
    private String cep;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    
    
}

package domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.hibernate.validator.constraints.Email;

@Embeddable
public class ContatoCliente implements Serializable {
    @Column(length = 20)
    private String telefone1;

    @Column(length = 20)
    private String telefone2;
    
    @Column(length = 50)
    @Email
    private String email;
    
    @Column
    private Boolean telefone1WhatsApp;
    
    @Column
    private Boolean telefone2WhatsApp;

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getTelefone1WhatsApp() {
        return telefone1WhatsApp;
    }

    public void setTelefone1WhatsApp(Boolean telefone1WhatsApp) {
        this.telefone1WhatsApp = telefone1WhatsApp;
    }

    public Boolean getTelefone2WhatsApp() {
        return telefone2WhatsApp;
    }

    public void setTelefone2WhatsApp(Boolean telefone2WhatsApp) {
        this.telefone2WhatsApp = telefone2WhatsApp;
    }
    
    
}

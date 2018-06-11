/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import entities.Context;
import entities.Repository;
import entities.annotations.ActionDescriptor;
import entities.annotations.Param;
import entities.annotations.ParameterDescriptor;
import entities.annotations.PropertyDescriptor;
import entities.annotations.UserRoles;
import entities.annotations.Username;
import entities.annotations.View;
import entities.annotations.Views;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author vitor
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(
                    name = "Autenticacao",
                    query = "From Usuario u "
                    + " Where u.userName = :username "
                    + " and u.password = :password"
            )
            ,
            @NamedQuery(
                    name = "Administradores",
                    query = "From Usuario u Where 'Admin' in elements( u.roles )"
            )
        }
)
@Views(
        {
            @View(
                    name = "Users",
                    title = "Usuários",
                    members = "userName,roles,novaSenhaEdicao()",
                    namedQuery = "From Usuario u Order By u.userName",
                    rows = 10,
                    template = "@CRUD+@TABLE+@PAGER",
                    roles = "Admin"
            )
            ,
            @View(
                    name = "Login",
                    title = "Login",
                    members = "[#userName;#password;login()]",
                    template = "@CRUD+@TABLE+@PAGER",
                    namedQuery = "Select new domain.Usuario()",
                    roles = "NOLOGGED"
            )
            ,
            @View(
                    name = "Logout",
                    title = "Sair do sistema",
                    members = "["
                            + "     [*userName;*roles];"
                            + "     [logout(),novaSenha()];"
                            + "]",
                    namedQuery = "From Usuario u Where u = :usuario ",
                    params = {
                        @Param(name = "usuario", value = "#{context.currentUser}")},
                    roles = "LOGGED"
            )
        }
)
public class Usuario implements Serializable {

    public enum Role {
        Admin, Cadastro, Vendedor
    }
    @Id
    @GeneratedValue
    private Integer id;

    @Username
    @Column(length = 25, unique = true)
    @NotEmpty(message = "Insira o usuário")
    private String userName;

    @Column(length = 32)
    @NotEmpty(message = "Insira a senha")
    @Type(type = "entities.security.Password")
    @PropertyDescriptor(secret = true, displayWidth = 25)
    private String password;

    @UserRoles
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private  List<Role> roles = new ArrayList<Role>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    
    

    public String login() {
        if (Repository.queryCount("Administradores") == 0) {
            // Criando um novo usuário com permissão de administrador
            Usuario admin = new Usuario();
            admin.userName = this.userName;
            admin.password = this.password;
            admin.roles.add(Role.Admin);
            // Persistindo alterações
            Repository.save(admin);
            // Salvando no contexto
            Context.setCurrentUser(admin);
            return "go:domain.Usuario@Users";
        }

        Usuario usuario = Repository.queryUnique("Autenticacao", this.userName, this.password);
        if (usuario == null) {
            throw new SecurityException("Combinação de usuário e senha inválida!");
        }
        Context.setCurrentUser(usuario);
        return "go:home";
    }

    public String logout() {
        Context.clear();
        return "go:domain.Usuario@Login";
    }
    
    @ActionDescriptor(
            displayName = "Definir Senha",
            preValidate = false
    )
    public String novaSenhaEdicao(
            @ParameterDescriptor(displayName = "Nova senha",
                    secret = true,
                    required = true) String newPassword,
            @ParameterDescriptor(displayName = "Confirmação", secret = true,
                    required = true) String rePassword) {
        if (newPassword.equals(rePassword)) {
            this.setPassword(newPassword);
            return "Senha definida com sucesso";
        } 
        
        throw new SecurityException("As senhas são divergentes");
    }


    public String novaSenha(
            @ParameterDescriptor(displayName = "Nova senha",
                    secret = true,
                    required = true) String newPassword,
            @ParameterDescriptor(displayName = "Confirmação", secret = true,
                    required = true) String rePassword) {
        if (newPassword.equals(rePassword)) {
            this.setPassword(newPassword);
            Repository.save(this);
            return "Senha alterada com sucesso.";
        } 
        
        throw new SecurityException("As senhas são divergentes");
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    
}

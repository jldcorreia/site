/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.usacucar.entidade;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jeffersonbarison
 */
@Entity
@Table(name = "ususuapp")
public class Ususuapp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "sq_usuario")
    private String sqUsuario;

    @Column(name = "ds_email")
    private String ds_email;

    @Column(name = "st_ativo")
    private String stAtivo;

    @Column(name = "ds_app")
    private String dsApp;
    
    @Column(name = "token")
    private String token;

    public String getSqUsuario() {
        return sqUsuario;
    }

    public void setSqUsuario(String sqUsuario) {
        this.sqUsuario = sqUsuario;
    }

    public String getDs_email() {
        return ds_email;
    }

    public void setDs_email(String ds_email) {
        this.ds_email = ds_email;
    }

    public String getStAtivo() {
        return stAtivo;
    }

    public void setStAtivo(String stAtivo) {
        this.stAtivo = stAtivo;
    }

    public String getDsApp() {
        return dsApp;
    }

    public void setDsApp(String dsApp) {
        this.dsApp = dsApp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.sqUsuario);
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
        final Ususuapp other = (Ususuapp) obj;
        if (!Objects.equals(this.sqUsuario, other.sqUsuario)) {
            return false;
        }
        return true;
    }

    
    
}

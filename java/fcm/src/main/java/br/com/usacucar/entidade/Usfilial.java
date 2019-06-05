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
@Table(name="usfilial")
public class Usfilial implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "cd_fil")
    private String cdFil;
    
    @Column(name = "ds_apelido")
    private String dsApelido;

    public String getCdFil() {
        return cdFil;
    }

    public void setCdFil(String cdFil) {
        this.cdFil = cdFil;
    }

    public String getDsApelido() {
        return dsApelido;
    }

    public void setDsApelido(String dsApelido) {
        this.dsApelido = dsApelido;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.cdFil);
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
        final Usfilial other = (Usfilial) obj;
        return Objects.equals(this.cdFil, other.cdFil);
    }

   
    
    
}

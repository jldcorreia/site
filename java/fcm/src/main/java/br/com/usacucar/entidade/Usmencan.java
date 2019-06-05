/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.usacucar.entidade;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author jeffersonbarison
 */
@Entity
@Table(name="usmencan")
public class Usmencan implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "sq_mensagem")
    private String sqMensagem;
    
    @Column(name = "tp_titulo")
    private String tpTitulo;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="hr_ocorrencia",nullable = false)    
    private Date hrOcorrencia; 
    
    @Column(name = "ds_mensagem")
    private String dsMensagem;
    
    
    @ManyToOne
    @JoinColumn(name = "cd_fil")
    private Usfilial filial;
    
    @Column(name = "st_enviado")
    private String stEnviado;
    
    @Column(name = "qt_horas_parada")
    private Integer qtHorasParada;
    
    @Column(name = "qt_minutos_parada")
    private Integer qtMinutosParada;
        
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="dt_usuinc",nullable = false)    
    private Date dtUsuinc;  

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="dt_usualt",nullable = false)    
    private Date dtUsualt; 
    
    @PrePersist
    public void preInsert() throws ParseException {
       DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.dtUsuinc = df.parse(df.format(new Date()));
    }
    
    
    @PreUpdate
    public void preupdate() throws ParseException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.dtUsualt =df.parse(df.format(new Date()));
    }

    public String getSqMensagem() {
        return sqMensagem;
    }

    public void setSqMensagem(String sqMensagem) {
        this.sqMensagem = sqMensagem;
    }

    public String getTpTitulo() {
        return tpTitulo;
    }

    public void setTpTitulo(String tpTitulo) {
        this.tpTitulo = tpTitulo;
    }

    public Date getHrOcorrencia() {
        return hrOcorrencia;
    }

    public void setHrOcorrencia(Date hrOcorrencia) {
        this.hrOcorrencia = hrOcorrencia;
    }

    public String getDsMensagem() {
        return dsMensagem;
    }

    public void setDsMensagem(String dsMensagem) {
        this.dsMensagem = dsMensagem;
    }

    public Usfilial getFilial() {
        return filial;
    }

    public void setFilial(Usfilial filial) {
        this.filial = filial;
    }

    public String getStEnviado() {
        return stEnviado;
    }

    public void setStEnviado(String stEnviado) {
        this.stEnviado = stEnviado;
    }

    public Date getDtUsuinc() {
        return dtUsuinc;
    }

    public void setDtUsuinc(Date dtUsuinc) {
        this.dtUsuinc = dtUsuinc;
    }

    public Integer getQtHorasParada() {
        return qtHorasParada;
    }

    public void setQtHorasParada(Integer qtHorasParada) {
        this.qtHorasParada = qtHorasParada;
    }

    public Integer getQtMinutosParada() {
        return qtMinutosParada;
    }

    public void setQtMinutosParada(Integer qtMinutosParada) {
        this.qtMinutosParada = qtMinutosParada;
    }

    public Date getDtUsualt() {
        return dtUsualt;
    }

    public void setDtUsualt(Date dtUsualt) {
        this.dtUsualt = dtUsualt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.sqMensagem);
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
        final Usmencan other = (Usmencan) obj;
        return Objects.equals(this.sqMensagem, other.sqMensagem);
    }

    

    
    
    
    
    
    
    
}

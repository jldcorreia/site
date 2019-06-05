/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.usacucar.managebean;

import br.com.usacucar.regra.UsmencanRegra;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jeffersonbarison
 */
@Named
@ViewScoped
public class UsuarioadMB implements Serializable {

    private static final long serialVersionUID = 1L;

       

    @Inject
    private UsmencanRegra usmencanRegra;

    private String vaNome = "parar Push Notification";
    private Boolean status;
    private String vaUltimadata;
    
    public String getVaNome() {
        return vaNome;
    }

    public void setVaNome(String vaNome) {
        this.vaNome = vaNome;
    }
    
    public void pararUsuario(){
        this.usmencanRegra.cancelTimer("pushInicial");
        this.usmencanRegra.cancelTimer("push");
        this.vaNome = "Iniciar Push Notification";
        
    }
    
    public void criarUsuario(){
        this.usmencanRegra.createTimer("push");        
        this.vaNome = "Push Notification";
    }

    public Boolean getStatus() {
        status = this.usmencanRegra.status() != null;
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getVaUltimadata() throws Exception {
        this.vaUltimadata = this.usmencanRegra.ultimaAtualizacao();
        return "Atualizado em: "+vaUltimadata;
    }

    public void setVaUltimadata(String vaUltimadata) {
        this.vaUltimadata = vaUltimadata;
    }

   

    
    
}

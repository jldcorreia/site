/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.usacucar.regra;

import br.com.usacucar.entidade.Usmencan;
import br.com.usacucar.entidade.Ususuapp;
import br.com.usacucar.regra.util.Fcm;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jeffersonbarison
 */
@Singleton
@Startup
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsmencanRegra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Resource
    TimerService timerService2;

    @PersistenceContext(name = "web")
    private EntityManager manager;
  
    List<Usmencan> listaUsmencan = new ArrayList<>();
    List<Ususuapp> listaUsusuapp = new ArrayList<>();
    
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date vDataarquivo;
    Date vData;
   

        
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void gravarMensagem(Usmencan usmencan) throws Exception{
        usmencan = this.manager.merge(usmencan);
        this.manager.persist(usmencan);     
    }
    
    private Usmencan localizarMensagem(Integer sqMensagem) throws Exception {
        Query query = this.manager.createQuery("select p from Usmencan  p where p.sqMensagen = :sqMensagem '",Usmencan.class);
        query.setParameter("sqMensagem", sqMensagem);
        Usmencan resultado = (Usmencan) query.getSingleResult();
        return resultado;       
    }

    private List<Usmencan> listarMensagem() throws Exception {
        Query query = this.manager.createQuery("select p from Usmencan p where  p.stEnviado = 'N'",Usmencan.class);        
        List<Usmencan> resultado =  query.getResultList();
        return resultado;       
    }

    private List<Ususuapp> listarUsuario() throws Exception {
        Query query = this.manager.createQuery("select p from Ususuapp  p where p.stAtivo = 'S' and p.dsApp = 'CANALUSACUCAR' and p.token is not null ",Ususuapp.class);        
        List<Ususuapp> resultado =  query.getResultList();
        return resultado;       
    }

    public void run() throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        
        this.listaUsmencan = this.listarMensagem();        
        for (Usmencan mensagem : this.listaUsmencan ){
            this.listaUsusuapp = this.listarUsuario();
            for (Ususuapp usuario : this.listaUsusuapp){
                String titulo = null;
                titulo = formato.format(mensagem.getHrOcorrencia());
                if ("P".equals(mensagem.getTpTitulo())){
                    titulo += " - PARADA USINA";
                }else{
                    titulo += " - RETOMADA USINA";
                }
                titulo += " - " +mensagem.getFilial().getDsApelido();
               Fcm.sendFCMNotification(usuario.getToken(), titulo, mensagem.getDsMensagem());
            }
            mensagem.setStEnviado("S");
            this.gravarMensagem(mensagem);
        }
        
    }
    
    
    @Schedule(second = "*/30", minute = "*", hour = "*", info = "pushInicial", persistent = false)
    public void schedule() {
        try {
            this.run();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @Timeout
    public void timeout(Timer timer) {
        try {
            this.run();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void cancelTimer(String timerName) {
        for (Object obj : timerService2.getTimers()) {
            Timer t = (Timer) obj;
            if (t.getInfo().equals(timerName)) {
                t.cancel();
            }
        }
    }

    public void createTimer(String timerName) {
        ScheduleExpression expression = new ScheduleExpression();
        TimerConfig config = new TimerConfig();
        expression.hour("*").minute("*").second("*/30");
        config.setInfo(timerName);
        config.setPersistent(false);
        this.timerService2.createCalendarTimer(expression, config);

    }

    public String status() {
        String status = null;
        for (Object obj : timerService2.getTimers()) {
            Timer t = (Timer) obj;
            status = t.getInfo().toString();
        }
        return status;
    }
    
    public String ultimaAtualizacao() throws Exception {
        String sql = "select to_char(sysdate,'dd/mm/yyyy hh24:mi:ss') ultima_data  from dual ";
        Query query = this.manager.createNativeQuery(sql);
        String resultado = query.getSingleResult().toString();
        return resultado;
    }
    
}

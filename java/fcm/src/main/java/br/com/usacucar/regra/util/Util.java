/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.usacucar.regra.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jeffersonbarison
 */
public class Util {
    
    
    
    @PersistenceContext(name = "web")
    private EntityManager manager;
    
    
   
    
    
   
}

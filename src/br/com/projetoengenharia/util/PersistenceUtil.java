/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author henri
 */
public class PersistenceUtil {
    
    private static EntityManagerFactory factory = 
            Persistence.createEntityManagerFactory("ProjetoEngenhariaPU");

    public static EntityManager createEntityManager() {
        return factory.createEntityManager();
    }
    
}

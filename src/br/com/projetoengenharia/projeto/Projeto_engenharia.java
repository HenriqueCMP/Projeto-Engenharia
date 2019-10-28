/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.projeto;

import br.com.projetoengenharia.util.PersistenceUtil;
import javax.persistence.EntityManager;

/**
 *
 * @author henri
 */
public class Projeto_engenharia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        EntityManager entityManager;
        
            entityManager = PersistenceUtil.createEntityManager();
            //entityManager.getTransaction().begin();


}
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.dao;

import br.com.projetoengenharia.model.Funcionario;
import br.com.projetoengenharia.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author henri
 */
public class FuncionarioDAO {
    private EntityManager entityManager;

    public void inserir(Funcionario funcionario) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(funcionario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void editar(Funcionario funcionario) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(funcionario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Funcionario getPorCPF(String cpf) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            return entityManager.find(Funcionario.class, cpf);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao Consultar");
//            return null;
        } finally {
            entityManager.close();
        }
        
    }
    

    public void remover(Funcionario funcionario) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            funcionario = entityManager.find(Funcionario.class, funcionario.getCpf());
            entityManager.remove(funcionario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Funcionario> listar() throws Exception{

        try {
            entityManager = PersistenceUtil.createEntityManager();
            return entityManager.createQuery("SELECT f FROM Funcionario f").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
        
    }
    
    public List<Funcionario> buscarPorNome(String busca) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            Query query = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.nome = :param");
            query.setParameter("param", "%" + busca + "%");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao Consultar");
        } finally {
            entityManager.close();
        }
    }
    
    public Funcionario buscarPorLogin(String busca) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            Query query = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.login = :param");
            query.setParameter("param", busca);
            return (Funcionario) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao Consultar");
        } finally {
            entityManager.close();
        }
    }
}

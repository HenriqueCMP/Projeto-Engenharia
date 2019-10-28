/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.dao;

import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author henri
 */
public class AlunoDAO {

    private EntityManager entityManager;

    public void inserir(Aluno aluno) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(aluno);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void editar(Aluno aluno) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(aluno);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Aluno getPorCPF(String cpf) {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            return entityManager.find(Aluno.class, cpf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }
    }

    public void remover(Aluno aluno) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            aluno = entityManager.find(Aluno.class, aluno.getCpf());
            entityManager.remove(aluno);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Aluno> listar(Aluno aluno) throws Exception {

        try {
            entityManager = PersistenceUtil.createEntityManager();
            Query query = entityManager.createQuery("SELECT a FROM Aluno a WHERE a.cpf = :param");
            query.setParameter("Param", aluno.getCpf());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao Consultar");
        } finally {
            entityManager.close();
        }
    }

}

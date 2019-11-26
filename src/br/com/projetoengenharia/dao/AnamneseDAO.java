/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.dao;

import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.model.Anamnese;
import br.com.projetoengenharia.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author henri
 */
public class AnamneseDAO {

    private EntityManager entityManager;

    public void inserir(Anamnese anamnese) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(anamnese);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void editar(Anamnese anamnese) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(anamnese);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Anamnese getPorId(long id) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            return entityManager.find(Anamnese.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao Consultar");
//            return null;
        } finally {
            entityManager.close();
        }
    }

    public void remover(Anamnese anamnese) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            anamnese = entityManager.find(Anamnese.class, anamnese.getId());
            entityManager.remove(anamnese);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Anamnese> listar(Aluno aluno) throws Exception {

        try {
            entityManager = PersistenceUtil.createEntityManager();
            Query query = entityManager.createQuery("SELECT a FROM anamnese a where a.aluno.cpf = :param");
            query.setParameter("param", aluno.getCpf());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            entityManager.close();
        }

    }

}

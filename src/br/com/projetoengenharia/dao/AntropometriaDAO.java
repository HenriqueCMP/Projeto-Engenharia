/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetoengenharia.dao;

import br.com.projetoengenharia.model.Aluno;
import br.com.projetoengenharia.model.Antropometria;
import br.com.projetoengenharia.util.PersistenceUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author henri
 */
public class AntropometriaDAO {
    private EntityManager entityManager;

    public void inserir(Antropometria antropometria) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(antropometria);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void editar(Antropometria antropometria) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(antropometria);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public Antropometria getPorId(long id) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            return entityManager.find(Antropometria.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erro ao Consultar");
//            return null;
        } finally {
            entityManager.close();
        }
    }

    public void remover(Antropometria antropometria) throws Exception {
        try {
            entityManager = PersistenceUtil.createEntityManager();
            entityManager.getTransaction().begin();
            antropometria = entityManager.find(Antropometria.class, antropometria.getId());
            entityManager.remove(antropometria);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Antropometria> listar(Aluno aluno) throws Exception {

        try {
            entityManager = PersistenceUtil.createEntityManager();
            Query query = entityManager.createQuery("SELECT a FROM antropometria a where a.aluno.cpf = :param");
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

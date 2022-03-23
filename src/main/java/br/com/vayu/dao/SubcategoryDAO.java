package br.com.vayu.dao;

import br.com.vayu.models.Subcategory;

import javax.persistence.EntityManager;
import java.util.List;

public class SubcategoryDAO {

    private final EntityManager entityManager;

    public SubcategoryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Subcategory findByIdJoinFetchCategory(int id) {
        String jpql = """
                SELECT sb
                FROM Subcategory sb
                JOIN FETCH sb.category
                WHERE sb.id = :id""";

        return entityManager.createQuery(jpql, Subcategory.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Subcategory> findAllByActiveTrueInOrder() {
        String jpql = """
                SELECT sb
                FROM Subcategory sb
                WHERE sb.active = true
                ORDER BY sb.order""";

        return entityManager
                .createQuery(jpql, Subcategory.class)
                .getResultList();
    }

    public void create(Subcategory subcategory) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(subcategory);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public int deleteAll() {
        entityManager.getTransaction().begin();
        String jpql = "DELETE FROM Subcategory ";

        try {
            int affectedRows = entityManager.createQuery(jpql).executeUpdate();
            entityManager.getTransaction().commit();
            return affectedRows;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

}

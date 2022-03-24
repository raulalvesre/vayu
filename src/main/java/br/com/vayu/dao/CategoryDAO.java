package br.com.vayu.dao;

import br.com.vayu.models.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDAO {

    private final EntityManager entityManager;

    public CategoryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Category findById(int id) {
        return entityManager.find(Category.class, id);
    }

    public List<Category> findAll() {
        String jpql = """
                SELECT ct
                FROM Category ct
                ORDER BY ct.order""";

        return entityManager
                .createQuery(jpql, Category.class)
                .getResultList();
    }

    public List<Category> findAllByActiveTrueInOrder() {
        String jpql = """
                SELECT ct
                FROM Category ct
                WHERE ct.active = true
                ORDER BY ct.order""";

        return entityManager
                .createQuery(jpql, Category.class)
                .getResultList();
    }

    public void create(Category category) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(category);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
           entityManager.getTransaction().rollback();
           throw new RuntimeException(e);
        }
    }

    public int deleteAll() {
        entityManager.getTransaction().begin();
        String jpql = "DELETE FROM Category";

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

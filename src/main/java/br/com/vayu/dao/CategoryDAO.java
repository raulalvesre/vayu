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
        entityManager.clear();

        return entityManager.find(Category.class, id);
    }

    public List<Category> findAllInOrder() {
        String jpql = """
                SELECT ct
                FROM Category ct
                ORDER BY ct.order""";

        entityManager.clear();

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
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(category);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public void update(Category category) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(category);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public void deactivateCategory(int id) {
        String jpql = """
                UPDATE Category
                SET active = 0
                WHERE id = :id""";

        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery(jpql)
                    .setParameter("id", id)
                    .executeUpdate();

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public int deleteAll() {
        String jpql = "DELETE FROM Category";

        try {
            entityManager.getTransaction().begin();
            int affectedRows = entityManager.createQuery(jpql).executeUpdate();
            entityManager.getTransaction().commit();
            return affectedRows;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

}

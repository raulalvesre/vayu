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

}

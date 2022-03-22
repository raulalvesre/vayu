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

}

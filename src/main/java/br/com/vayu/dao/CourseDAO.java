package br.com.vayu.dao;

import br.com.vayu.models.Course;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseDAO {

    private final EntityManager entityManager;

    public CourseDAO(EntityManager em) {
        this.entityManager = em;
    }

    public List<Course> findAllByVisibleTrue() {
        String jpql = """
                SELECT c
                FROM Course c
                WHERE c.visible = true""";

        return entityManager
                .createQuery(jpql, Course.class)
                .getResultList();
    }

    public void create(Course course) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(course);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public int makeAllVisible() {
        entityManager.getTransaction().begin();

        String jpql = """
                UPDATE Course
                SET visible = true
                WHERE visible = false""";

        try {
            int affectedRows = entityManager.createQuery(jpql).executeUpdate();
            entityManager.getTransaction().commit();
            return affectedRows;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public void delete(Course course) {
        entityManager.getTransaction().begin();

        try {
            course = entityManager.merge(course);
            entityManager.remove(course);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public int deleteAll() {
        entityManager.getTransaction().begin();
        String jpql = "DELETE FROM Course";

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

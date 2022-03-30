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
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(course);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public int makeAllVisible() {
        String jpql = """
                UPDATE Course
                SET visible = true
                WHERE visible = false""";

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

    public void delete(Course course) {
        try {
            entityManager.getTransaction().begin();
            course = entityManager.merge(course);
            entityManager.remove(course);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        }
    }

    public int deleteAll() {
        String jpql = "DELETE FROM Course";

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

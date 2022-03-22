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

    public void createCourse(Course course) {
        entityManager.persist(course);
    }

    public int makeAllCoursesVisible() {
        String jpql = """
                UPDATE Course
                SET visible = true
                WHERE visible = false""";

        return entityManager.createQuery(jpql).executeUpdate();
    }

    public void deleteCourse(Course course) {
        course = entityManager.merge(course);
        entityManager.remove(course);
    }

}

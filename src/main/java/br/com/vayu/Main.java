package br.com.vayu;

import br.com.vayu.dao.CategoryDAO;
import br.com.vayu.dao.CourseDAO;
import br.com.vayu.dao.SubcategoryDAO;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import br.com.vayu.services.HtmlCreatorService;
import br.com.vayu.util.JPAUtil;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        CategoryDAO categoryDAO = new CategoryDAO(em);
        SubcategoryDAO subcategoryDAO = new SubcategoryDAO(em);
        CourseDAO courseDAO = new CourseDAO(em);

        HtmlCreatorService.generateCoursesHtml(categoryDAO, subcategoryDAO, courseDAO);

        Subcategory subcategory = subcategoryDAO.findByIdJoinFetchCategory(5);

        Course newCourse = new Course("raul-history",
                "Historia do Raul",
                10,
                false,
                "Intelectuais",
                "Raul",
                "Tudo sobre Raul",
                "raullogia",
                subcategory);

        courseDAO.create(newCourse);
        System.out.println("ID curso criado: " + newCourse.getId());

        int updatedRows = courseDAO.makeAllVisible();
        System.out.println("UPDATE ROWS: " + updatedRows);

        courseDAO.delete(newCourse);
    }

}

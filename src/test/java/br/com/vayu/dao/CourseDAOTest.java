package br.com.vayu.dao;

import br.com.vayu.TestJPAUtil;
import br.com.vayu.builders.CategoryBuilder;
import br.com.vayu.builders.CourseBuilder;
import br.com.vayu.builders.SubcategoryBuilder;
import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseDAOTest {

    private EntityManager entityManager;
    private CourseDAO courseDAO;
    private Subcategory subcategory;

    private final String code = "code";
    private final String name = "name";
    private final String targetAudience = "targetAudience";
    private final String instructorName = "raul";
    private final String syllabus = "syllabus";
    private final String developedAbilities = "dev abilities";

    @BeforeEach
    public void beforeEach() {
        entityManager = TestJPAUtil.getEntityManager();
        courseDAO = new CourseDAO(entityManager);
        entityManager.getTransaction().begin();

        String studyGuide = "study guide";
        String description = "desc";

        Category category = new CategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(1)
                .iconPath("icon path")
                .colorCode("#FFFFFF")
                .build();

        entityManager.persist(category);

        subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(1)
                .category(category)
                .build();

        entityManager.persist(subcategory);
    }

    @AfterEach
    public void afterEach() {
        entityManager.getTransaction().rollback();
    }

    @Test
    void findAllByVisibleTrue_should_return_active_courses() {
        Course course1 = new CourseBuilder()
                .code(code)
                .name(name)
                .estimatedHoursToFinish(1)
                .visible(true)
                .targetAudience(targetAudience)
                .instructorName(instructorName)
                .syllabus(syllabus)
                .developedAbilities(developedAbilities)
                .subcategory(subcategory)
                .build();

        Course course2 = new CourseBuilder()
                .code(code)
                .name(name)
                .estimatedHoursToFinish(1)
                .visible(false)
                .targetAudience(targetAudience)
                .instructorName(instructorName)
                .syllabus(syllabus)
                .developedAbilities(developedAbilities)
                .subcategory(subcategory)
                .build();

        entityManager.persist(course1);
        entityManager.persist(course2);

        List<Course> receivedList = courseDAO.findAllByVisibleTrue();

        assertNotNull(receivedList);
        assertEquals(receivedList.size(), 1);
        assertEquals(receivedList.get(0), course1);
    }

    @Test
    void makeAllCoursesVisible_should_make_all_courses_visible() {
        Course course1 = new CourseBuilder()
                .code(code)
                .name(name)
                .estimatedHoursToFinish(1)
                .visible(false)
                .targetAudience(targetAudience)
                .instructorName(instructorName)
                .syllabus(syllabus)
                .developedAbilities(developedAbilities)
                .subcategory(subcategory)
                .build();

        Course course2 = new CourseBuilder()
                .code(code)
                .name(name)
                .estimatedHoursToFinish(1)
                .visible(false)
                .targetAudience(targetAudience)
                .instructorName(instructorName)
                .syllabus(syllabus)
                .developedAbilities(developedAbilities)
                .subcategory(subcategory)
                .build();

        entityManager.persist(course1);
        entityManager.persist(course2);

        int affectedRows = courseDAO.makeAllCoursesVisible();

        assertEquals(affectedRows, 2);
    }

}

package br.com.vayu.dao;

import br.com.vayu.TestJPAUtil;
import br.com.vayu.builders.CategoryBuilder;
import br.com.vayu.builders.CourseBuilder;
import br.com.vayu.builders.SubcategoryBuilder;
import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CourseDAOTest {

    private static CategoryDAO categoryDAO;
    private static SubcategoryDAO subcategoryDAO;
    private static CourseDAO courseDAO;
    private static Subcategory subcategory;

    private static final String code = "code";
    private static final String name = "name";
    private final String targetAudience = "targetAudience";
    private final String instructorName = "raul";
    private final String syllabus = "syllabus";
    private final String developedAbilities = "dev abilities";

    @BeforeAll
    static void beforeAll() {
        EntityManager entityManager = TestJPAUtil.getEntityManager();
        categoryDAO = new CategoryDAO(entityManager);
        subcategoryDAO = new SubcategoryDAO(entityManager);
        courseDAO = new CourseDAO(entityManager);

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

        categoryDAO.create(category);

        subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(1)
                .category(category)
                .build();

        subcategoryDAO.create(subcategory);
    }

    @AfterEach
    void afterEach() {
        courseDAO.deleteAll();
    }

    @AfterAll
    static void afterAll() {
        subcategoryDAO.deleteAll();
        categoryDAO.deleteAll();
    }

    @Test
    void findAllByVisibleTrue__should_return_active_courses() {
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

        courseDAO.create(course1);
        courseDAO.create(course2);

        List<Course> receivedList = courseDAO.findAllByVisibleTrue();

        assertNotNull(receivedList);
        assertEquals(receivedList.size(), 1);
        assertEquals(receivedList.get(0), course1);
    }

    @Test
    void findAllByVisibleTrue__should_return_empty_list() {
        List<Course> receivedList = courseDAO.findAllByVisibleTrue();

        assertNotNull(receivedList);
        assertEquals(0 , receivedList.size());
    }

    @Test
    void makeAllCoursesVisible__should_make_two_courses_visible() {
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

        courseDAO.create(course1);
        courseDAO.create(course2);

        int affectedRows = courseDAO.makeAllVisible();

        assertEquals(affectedRows, 2);
    }

    @Test
    void makeAllCoursesVisible__should_make_zero_courses_visible() {
        int affectedRows = courseDAO.makeAllVisible();

        assertEquals(0, affectedRows);
    }

}

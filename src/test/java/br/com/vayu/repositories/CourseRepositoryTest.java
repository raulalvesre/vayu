package br.com.vayu.repositories;

import br.com.vayu.builders.CategoryBuilder;
import br.com.vayu.builders.CourseBuilder;
import br.com.vayu.builders.SubcategoryBuilder;
import br.com.vayu.dto.CourseFormDTO;
import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import br.com.vayu.projections.CourseMinifiedProjection;
import br.com.vayu.projections.DashboardInstructorProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TestEntityManager em;

    private Category activeCategory;
    private Subcategory activeSubcategory;

    private final String code = "code";
    private final String name = "name";
    private final int estimatedHoursToFinish = 1;
    private final String targetAudience = "targetAudience";
    private final String instructorName = "instructorName";
    private final String syllabus = "syllabus";
    private final String developedAbilities = "developedAbilities";

    @BeforeEach
    void beforeEach() {
        this.activeCategory = createAndSaveCategory(true);
        this.activeSubcategory = createAndSaveSubcategory(true);

        em.persist(activeCategory);
        em.persist(activeSubcategory);
    }

    @Test
    void findByCodeAsFormDto__should_return_as_form_dto_by_code() {
        createAndSaveCourse();

        Optional<CourseFormDTO> bdCourseOptional = courseRepository
                .findByCodeAsFormDto("code");
        assertTrue(bdCourseOptional.isPresent());

        CourseFormDTO bdCourse = bdCourseOptional.get();

        assertEquals(name, bdCourse.getName());
        assertEquals(code, bdCourse.getCode());
        assertEquals(estimatedHoursToFinish, bdCourse.getEstimatedHoursToFinish());
        assertTrue(bdCourse.isVisible());
        assertEquals(targetAudience, bdCourse.getTargetAudience());
        assertEquals(instructorName, bdCourse.getInstructorName());
        assertEquals(syllabus, bdCourse.getSyllabus());
        assertEquals(developedAbilities, bdCourse.getDevelopedAbilities());
        assertEquals(activeSubcategory.getCode(), bdCourse.getSubcategoryCode());
    }
    @Test
    void findAllForCategoryApi__should_return_all_visible_with_subcategory_active_and_category_active_including_category_and_subcategory() {
        Course course1 = createAndSaveCourse();
        Course course2 = createAndSaveCourse();

        List<Course> bdCourses = courseRepository.findAllForCategoryApi();

        assertEquals(2, bdCourses.size());
        assertEquals(course1, bdCourses.get(0));
        assertEquals(course2, bdCourses.get(1));
    }

    @Test
    void findAllForCategoryApi__should_return_empty_list_when_subcategory_inactive() {
        Subcategory inactiveSubcategory = createAndSaveSubcategory(false);
        createAndSaveCourse(inactiveSubcategory);
        createAndSaveCourse(inactiveSubcategory);

        List<Course> bdCourses = courseRepository.findAllForCategoryApi();

        assertTrue(bdCourses.isEmpty());
    }

    @Test
    void findAllForCategoryApi__should_return_empty_list_when_category_inactive() {
        Category inactiveCategory = createAndSaveCategory(false);

        Subcategory subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description("description")
                .studyGuide("studyGuide")
                .active(true)
                .order(0)
                .category(inactiveCategory)
                .build();

        em.persist(subcategory);

        createAndSaveCourse(subcategory);
        createAndSaveCourse(subcategory);

        List<Course> bdCourses = courseRepository.findAllForCategoryApi();

        assertTrue(bdCourses.isEmpty());
    }

    @Test
    void getDashboardInstructorView__should_return_correct_projection() {
        createAndSaveCourse();

        DashboardInstructorProjection dashboardInstructorView = courseRepository.getDashboardInstructorView();

        assertEquals(instructorName, dashboardInstructorView.getName());
        assertEquals(1, dashboardInstructorView.getTotalCourses());
    }

    @Test
    void getDashboardInstructorView__should_return_null() {
        DashboardInstructorProjection dashboardInstructorView = courseRepository.getDashboardInstructorView();

        assertNull(dashboardInstructorView);
    }

    @Test
    void findAllMinifiedBySubcategoryCode__should_return_courses_minified_by_subcategory_code() {
        createAndSaveCourse();
        createAndSaveCourse();

        List<CourseMinifiedProjection> bdCourses = courseRepository.findAllMinifiedBySubcategoryCode("code");

        assertEquals(2, bdCourses.size());

        assertEquals(name, bdCourses.get(0).getName());
        assertEquals(code, bdCourses.get(0).getCode());
        assertTrue(bdCourses.get(0).isVisible());

        assertEquals(name, bdCourses.get(1).getName());
        assertEquals(code, bdCourses.get(1).getCode());
        assertTrue(bdCourses.get(1).isVisible());
    }

    @Test
    void findAllMinifiedBySubcategoryCode__should_return_empty_list_when_subcategory_code_incorrect() {
        createAndSaveCourse();

        List<CourseMinifiedProjection> bdCourses = courseRepository.findAllMinifiedBySubcategoryCode("idontexist");

        assertTrue(bdCourses.isEmpty());
    }

    @Test
    void findAllForCategoryPublicPage__should_return_visible_courses_with_active_subcategory_by_category_code_including_category_and_subcategory() {
        Course course1 = createAndSaveCourse();
        Course course2 = createAndSaveCourse();

        List<Course> bdCourses = courseRepository.findAllForCategoryPublicPage(code);

        assertEquals(2, bdCourses.size());
        assertEquals(course1, bdCourses.get(0));
        assertEquals(course2, bdCourses.get(1));
    }

    @Test
    void findAllForCategoryPublicPage__should_return_empty_list_when_category_code_incorrect() {
        createAndSaveCourse();

        List<Course> bdCourses = courseRepository.findAllForCategoryPublicPage("idontexist");

        assertTrue(bdCourses.isEmpty());
    }

    @Test
    void findAllForCategoryPublicPage__should_return_empty_list_when_subcategory_inactive() {
        Subcategory inactiveSubcategory = createAndSaveSubcategory(false);
        createAndSaveCourse(inactiveSubcategory);

        List<Course> bdCourses = courseRepository.findAllForCategoryPublicPage("code");

        assertTrue(bdCourses.isEmpty());
    }

    @Test
    void findAllForCategoryPublicPage__should_return_empty_list_when_course_invisible() {
        Course course = new CourseBuilder()
                .code(code)
                .name(name)
                .estimatedHoursToFinish(1)
                .visible(false)
                .targetAudience("targetAudience")
                .instructorName("instructorName")
                .syllabus("syllabus")
                .developedAbilities("developedAbilities")
                .subcategory(activeSubcategory)
                .build();

        em.persist(course);

        List<Course> bdCourses = courseRepository.findAllForCategoryPublicPage("code");

        assertTrue(bdCourses.isEmpty());
    }

    @Test
    void findPageMinifiedBySubcategoryCode__should_return_minified_course_list_by_subcategory_code_in_content() {
        createAndSaveCourse();
        createAndSaveCourse();

        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<CourseMinifiedProjection> coursePage =
                courseRepository.findPageMinifiedBySubcategoryCode(code, pageRequest);
        List<CourseMinifiedProjection> courses = coursePage.getContent();

        assertEquals(2, courses.size());

        assertEquals(name, courses.get(0).getName());
        assertEquals(code, courses.get(0).getCode());
        assertTrue(courses.get(0).isVisible());

        assertEquals(name, courses.get(1).getName());
        assertEquals(code, courses.get(1).getCode());
        assertTrue(courses.get(1).isVisible());
    }

    @Test
    void findPageMinifiedBySubcategoryCode__should_return_empty_list_when_subcategory_code_incorrect() {
        createAndSaveCourse();

        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<CourseMinifiedProjection> coursePage =
                courseRepository.findPageMinifiedBySubcategoryCode("idontexist", pageRequest);
        List<CourseMinifiedProjection> courses = coursePage.getContent();

        assertTrue(courses.isEmpty());
    }

    private Course createAndSaveCourse() {
        Course course = new CourseBuilder()
                .code(code)
                .name(name)
                .estimatedHoursToFinish(estimatedHoursToFinish)
                .visible(true)
                .targetAudience(targetAudience)
                .instructorName(instructorName)
                .syllabus(syllabus)
                .developedAbilities(developedAbilities)
                .subcategory(activeSubcategory)
                .build();

        return em.persist(course);
    }

    private Course createAndSaveCourse(Subcategory subcategory) {
        Course course = new CourseBuilder()
                .code(code)
                .name(name)
                .estimatedHoursToFinish(1)
                .visible(true)
                .targetAudience("targetAudience")
                .instructorName("instructorName")
                .syllabus("syllabus")
                .developedAbilities("developedAbilities")
                .subcategory(subcategory)
                .build();

        return em.persist(course);
    }

    private Subcategory createAndSaveSubcategory(boolean active) {
        Subcategory subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description("description")
                .studyGuide("studyGuide")
                .active(active)
                .order(0)
                .category(activeCategory)
                .build();

        return em.persist(subcategory);
    }

    private Category createAndSaveCategory(boolean active) {
        Category category = new CategoryBuilder()
                .code(code)
                .name(name)
                .description("description")
                .studyGuide("studyGuide")
                .active(active)
                .order(0)
                .iconPath("iconPath")
                .colorCode("#FFF")
                .build();

        return em.persist(category);
    }

}
package br.com.vayu.services;

import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseServiceTest {

    private static List<Subcategory> subcategories;

    @BeforeAll
    static void initialize_subcategories_list() {
        var categories = CategoryService.getCategoriesListFromCsv(
                "category/valid_categories.csv");

        subcategories = SubcategoryService.getSubcategoriesListFromCsv(
                "subcategory/valid_subcategories.csv", categories);
    }

    @Test
    void should_return_list_identical_to_csv_data() {
        List<Course> receivedList = CourseService.getCourseListFromCsv(
                "course/valid_courses.csv",
                subcategories);

        Course course1 = new Course("code1",
                "name1",
                1,
                true,
                "targetAudience1",
                "Instructor1",
                "syllabus1",
                "dev_abilities1",
                SubcategoryService.getSubcategoryByCode(subcategories, "code1"));

        Course course2 = new Course("code2",
                "name2",
                2,
                false,
                "targetAudience2",
                "Instructor2",
                "syllabus2",
                "dev_abilities2",
                SubcategoryService.getSubcategoryByCode(subcategories, "code2"));

        Course course3 = new Course("code3",
                "name3",
                3,
                true,
                "targetAudience3",
                "Instructor2",
                "syllabus3",
                "dev_abilities3",
                SubcategoryService.getSubcategoryByCode(subcategories, "code2"));

        List<Course> expectedList = List.of(course1, course2, course3);

        assertNotNull(receivedList);
        assertEquals(receivedList.size(), 3);
        assertEquals(receivedList, expectedList);
    }

    @Test
    void should_return_empty_list() {
        List<Course> receivedList = CourseService.getCourseListFromCsv(
                "course/empty_courses.csv",
                subcategories);

        assertNotNull(receivedList);
        assertEquals(receivedList.size(), 0);
    }

    @Test
    void should_throw_exception_if_csv_does_not_exist() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseService.getCourseListFromCsv("I dont exist", subcategories));
    }

    @Test
    void should_throw_exception_if_csv_contains_empty_name() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseService.getCourseListFromCsv("course/empty_name_course.csv", subcategories));
    }

    @Test
    void should_throw_exception_if_csv_contains_empty_code() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseService.getCourseListFromCsv("course/empty_code_course.csv", subcategories));
    }

    @Test
    void should_throw_exception_if_csv_contains_invalid_code() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseService.getCourseListFromCsv("course/invalid_code_course.csv", subcategories));
    }

    @Test
    void should_throw_exception_if_csv_contains_estimated_hours_to_finish_smaller_than_one() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseService.getCourseListFromCsv(
                        "course/course_with_estimated_hours_to_finish_smaller_than_one.csv",
                        subcategories));
    }

    @Test
    void should_throw_exception_if_csv_contains_estimated_hours_to_finish_bigger_than_one() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseService.getCourseListFromCsv(
                        "course/course_with_estimated_hours_to_finish_bigger_than_one.csv", subcategories));
    }

    @Test
    void should_throw_exception_if_csv_contains_empty_instructor_name() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseService.getCourseListFromCsv(
                        "course/course_with_empty_instructor_name.csv", subcategories));
    }

    @Test
    void should_throw_exception_if_csv_contains_empty_subcategory_code() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseService.getCourseListFromCsv("course/course_with_empty_subcategory_code.csv", subcategories));
    }

    @Test
    void should_print_private_courses() {
        List<Course> receivedList = CourseService.getCourseListFromCsv(
                "course/valid_courses.csv",
                subcategories);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CourseService.printIfThereIsPrivateCourse(receivedList);

        String expectedOutput = """
                PRIVATE COURSES:
                Course{code='code2', name='name2', estimatedHoursToFinish=2, visible=false, targetAudience='targetAudience2', instructorName='Instructor2', syllabus='syllabus2', developedAbilities='dev_abilities2', subCategory=name2}
                """;

        assertEquals(outContent.toString(), expectedOutput);
    }

    @Test
    void should_print_instructor_names_without_repetition() {
        List<Course> receivedList = CourseService.getCourseListFromCsv(
                "course/valid_courses.csv",
                subcategories);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CourseService.printInstructorNames(receivedList);

        String expectedOutput = """
                INSTRUCTORS NAMES:
                Instructor1
                Instructor2
                """;

        assertEquals(outContent.toString(), expectedOutput);
    }

    @Test
    void should_print_instructor_name_and_how_many_courses_they_have() {
        List<Course> receivedList = CourseService.getCourseListFromCsv(
                "course/valid_courses.csv",
                subcategories);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CourseService.printInstructorNameAndHowManyCoursesTheyHave(receivedList);

        String expectedOutput = """
                Instructor1 has 1 courses
                Instructor2 has 2 courses
                """;

        assertEquals(outContent.toString(), expectedOutput);
    }

}

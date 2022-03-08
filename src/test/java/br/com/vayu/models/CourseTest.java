package br.com.vayu.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseTest {

    private static Subcategory subcategory;

    @BeforeAll
    static void initialize_subcategory() {
        var category = new Category("code1",
                "name1",
                "desc1",
                null,
                true,
                1,
                "https://www.test.com.br/icon1.png",
                "#000000");

        subcategory = new Subcategory("code1",
                "name1",
                "desc1",
                null,
                true,
                1,
                category);
    }

    @Test
    void should_create_course() {
        new Course("code1",
                "name1",
                1,
                true,
                "targetAudience1",
                "Instructor1",
                "syllabus1",
                "dev_abilities1",
                subcategory);
    }

    @Test
    void should_throw_exception_if_code_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(null,
                        "name1",
                        1,
                        true,
                        "targetAudience1",
                        "Instructor1",
                        "syllabus1",
                        "dev_abilities1",
                        subcategory));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_code_is_blank(String code) {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(code,
                        "name1",
                        1,
                        true,
                        "targetAudience1",
                        "Instructor1",
                        "syllabus1",
                        "dev_abilities1",
                        subcategory));
    }

    @Test
    void should_throw_exception_if_code_is_invalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("INVALID",
                        "name1",
                        1,
                        true,
                        "targetAudience1",
                        "Instructor1",
                        "syllabus1",
                        "dev_abilities1",
                        subcategory));
    }

    @Test
    void should_throw_exception_if_name_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("code1",
                        null,
                        1,
                        true,
                        "targetAudience1",
                        "Instructor1",
                        "syllabus1",
                        "dev_abilities1",
                        subcategory));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_name_is_blank(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("code1",
                        name,
                        1,
                        true,
                        "targetAudience1",
                        "Instructor1",
                        "syllabus1",
                        "dev_abilities1",
                        subcategory));
    }

    @Test
    void should_throw_exception_if_estimated_hours_to_finish_is_less_than_one() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("code1",
                        "name1",
                        0,
                        true,
                        "targetAudience1",
                        "Instructor1",
                        "syllabus1",
                        "dev_abilities1",
                        subcategory));
    }

    @Test
    void should_throw_exception_if_estimated_hours_to_finish_is_bigger_than_twenty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("code1",
                        "name1",
                        21,
                        true,
                        "targetAudience1",
                        "Instructor1",
                        "syllabus1",
                        "dev_abilities1",
                        subcategory));
    }

    @Test
    void should_throw_exception_if_instructor_name_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("code1",
                        "name1",
                        1,
                        true,
                        "targetAudience1",
                        null,
                        "syllabus1",
                        "dev_abilities1",
                        subcategory));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_instructor_name_is_blank(String instructorName) {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("code1",
                        "name1",
                        1,
                        true,
                        "targetAudience1",
                        instructorName,
                        "syllabus1",
                        "dev_abilities1",
                        subcategory));
    }

    @Test
    void should_throw_exception_if_subcategory_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("code1",
                        "name1",
                        1,
                        true,
                        "targetAudience1",
                        "Instructor1",
                        "syllabus1",
                        "dev_abilities1",
                        null));
    }

}

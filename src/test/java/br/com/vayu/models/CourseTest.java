package br.com.vayu.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CourseTest {

    private final String code = "code";
    private final String name = "name";
    private final int estimatedHoursToFinish = 1;
    private final boolean visible = true;
    private final String targetAudience = "audience";
    private final String instructorName = "Joao";
    private final String syllabus = "syllabus";
    private final String developedAbilities = "dev abilities";
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
        assertDoesNotThrow(() -> new Course(code,
                name,
                estimatedHoursToFinish,
                visible,
                targetAudience,
                instructorName,
                syllabus,
                developedAbilities,
                subcategory));
    }

    @Test
    void should_throw_exception_if_code_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(null,
                        name,
                        estimatedHoursToFinish,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        subcategory));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_code_is_blank(String codeParam) {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(codeParam,
                        name,
                        estimatedHoursToFinish,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        subcategory));
    }

    @Test
    void should_throw_exception_if_code_is_invalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course("INVALID",
                        name,
                        estimatedHoursToFinish,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        subcategory));
    }

    @Test
    void should_throw_exception_if_name_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(code,
                        null,
                        estimatedHoursToFinish,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        subcategory));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_name_is_blank(String nameParam) {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(code,
                        nameParam,
                        estimatedHoursToFinish,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        subcategory));
    }

    @Test
    void should_throw_exception_if_estimated_hours_to_finish_is_less_than_one() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(code,
                        name,
                        0,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        subcategory));
    }

    @Test
    void should_throw_exception_if_estimated_hours_to_finish_is_bigger_than_twenty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(code,
                        name,
                        21,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        subcategory));
    }

    @Test
    void should_throw_exception_if_instructor_name_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(code,
                        null,
                        estimatedHoursToFinish,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        subcategory));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_instructor_name_is_blank(String instructorNameParam) {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(code,
                        name,
                        estimatedHoursToFinish,
                        visible,
                        targetAudience,
                        instructorNameParam,
                        syllabus,
                        developedAbilities,
                        subcategory));
    }

    @Test
    void should_throw_exception_if_subcategory_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Course(code,
                        name,
                        estimatedHoursToFinish,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        null));
    }

}

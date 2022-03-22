package br.com.vayu.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubcategoryTest {

    private final String code = "code";
    private final String name = "name";
    private final String desc = "dec";
    private final boolean active = true;
    private final int order = 1;
    private static Category category;

    @BeforeAll
    static void initialize_category() {
        category = new Category("code1",
                "name1",
                "desc1",
                null,
                true,
                1,
                "https://www.test.com.br/icon1.png",
                "#000000");
    }

    @Test
    void should_create_subcategory() {
        assertDoesNotThrow(() -> new Subcategory(code,
                name,
                desc,
                null,
                active,
                order,
                category));
    }

    @Test
    void should_throw_exception_if_code_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory(null,
                        name,
                        desc,
                        null,
                        active,
                        order,
                        category));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_code_is_blank(String codeParam) {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory(null,
                        name,
                        desc,
                        null,
                        active,
                        order,
                        category));
    }

    @Test
    void should_throw_exception_if_code_is_invalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory("INVALID",
                        name,
                        desc,
                        null,
                        active,
                        order,
                        category));
    }

    @Test
    void should_throw_exception_if_name_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory(code,
                        null,
                        desc,
                        null,
                        active,
                        order,
                        category));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_name_is_blank(String nameParam) {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory(code,
                        nameParam,
                        desc,
                        null,
                        active,
                        order,
                        category));
    }

    @Test
    void should_throw_exception_if_category_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory(code,
                        name,
                        desc,
                        null,
                        active,
                        order,
                        null));
    }

}

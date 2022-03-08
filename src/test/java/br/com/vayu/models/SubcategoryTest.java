package br.com.vayu.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubcategoryTest {

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
        new Subcategory("code1",
                "name1",
                "desc1",
                null,
                true,
                1,
                category);
    }

    @Test
    void should_throw_exception_if_code_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory(null,
                        "name1",
                        "desc1",
                        null,
                        true,
                        1,
                        category));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_code_is_blank(String code) {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory(code,
                        "name1",
                        "desc1",
                        null,
                        true,
                        1,
                        category));
    }

    @Test
    void should_throw_exception_if_code_is_invalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory("INVALID",
                        "name1",
                        "desc1",
                        null,
                        true,
                        1,
                        category));
    }

    @Test
    void should_throw_exception_if_name_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory("code1",
                        null,
                        "desc1",
                        null,
                        true,
                        1,
                        category));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_name_is_blank(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory("code1",
                        name,
                        "desc1",
                        null,
                        true,
                        1,
                        category));
    }

    @Test
    void should_throw_exception_if_category_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Subcategory("code1",
                        "name1",
                        "desc1",
                        null,
                        true,
                        1,
                        null));
    }

}

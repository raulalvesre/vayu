package br.com.vayu.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryTest {

    @Test
    void should_throw_exception_if_code_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category(null,
                        "name1",
                        "desc1",
                        null,
                        true,
                        1,
                        "https://www.test.com.br/icon1.png",
                        "#000000"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_code_is_blank(String code) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category(code,
                        "name1",
                        "desc1",
                        null,
                        true,
                        1,
                        "https://www.test.com.br/icon1.png",
                        "#000000"));
    }

    @Test
    void should_throw_exception_if_code_is_invalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("INVALID",
                        "name1",
                        "desc1",
                        null,
                        true,
                        1,
                        "https://www.test.com.br/icon1.png",
                        "#000000"));
    }

    @Test
    void should_throw_exception_if_name_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("code1",
                        null,
                        "desc1",
                        null,
                        true,
                        1,
                        "https://www.test.com.br/icon1.png",
                        "#000000"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_name_is_blank(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("code1",
                        name,
                        "desc1",
                        null,
                        true,
                        1,
                        "https://www.test.com.br/icon1.png",
                        "#000000"));
    }

}

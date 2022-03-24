package br.com.vayu.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryTest {

    private final String code = "code";
    private final String name = "name";
    private final String description = "desc";
    private final boolean active = true;
    private final int order = 1;
    private final String iconPath = "https://www.test.com.br/icon.png";
    private final String colorCode = "#FFFFFF";

    @Test
    void should_create_category() {
        assertDoesNotThrow(() -> new Category(code,
                name,
                description,
                null,
                active,
                order,
                iconPath,
                colorCode));
    }

    @Test
    void should_throw_exception_if_code_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category(null,
                        name,
                        description,
                        null,
                        active,
                        order,
                        iconPath,
                        colorCode));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_code_is_blank(String codeParam) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category(codeParam,
                        name,
                        description,
                        null,
                        active,
                        order,
                        iconPath,
                        colorCode));
    }

    @Test
    void should_throw_exception_if_code_is_invalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category("INVALIDO",
                        name,
                        description,
                        null,
                        active,
                        order,
                        iconPath,
                        colorCode));
    }

    @Test
    void should_throw_exception_if_name_is_null() {
        assertThrows(IllegalArgumentException.class,
                () -> new Category(code,
                        null,
                        description,
                        null,
                        active,
                        order,
                        iconPath,
                        colorCode));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_name_is_blank(String nameParam) {
        assertThrows(IllegalArgumentException.class,
                () -> new Category(code,
                        nameParam,
                        description,
                        null,
                        active,
                        order,
                        iconPath,
                        colorCode));
    }

}

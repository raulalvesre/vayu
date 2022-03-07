package br.com.vayu.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceTest {

    @Test
    void should_throw_exception_if_object_is_null() {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfItIsNull("test", null));
    }

    @Test
    void should_throw_exception_if_string_is_null() {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfIsBlankString("", null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_string_is_blank(String str) {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfIsBlankString("", str));
    }

    @Test
    void should_throw_exception_if_code_is_null() {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfItIsValidCode(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_code_is_blank(String str) {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfItIsValidCode(str));
    }

    @Test
    void should_throw_exception_if_code_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfItIsValidCode("INVALIDO"));
    }

    @Test
    void should_throw_exception_if_int_is_less_than_range_min() {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfIntIsWithinRange("", 1, 2, 3));
    }

    @Test
    void should_throw_exception_if_int_is_bigger_than_range_max() {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfIntIsWithinRange("", 4, 2, 3));
    }

    @Test
    void should_throw_exception_if_hex_color_code_is_null() {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfIsValidHexColorCode("", null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_hex_color_code_is_blank(String str) {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfIsValidHexColorCode("", str));
    }

    @Test
    void should_throw_exception_if_hex_color_code_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfIsValidHexColorCode("", "INVALIDO"));
    }

    @Test
    void should_throw_exception_if_url_is_null() {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfItIsValidURL("",  null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void should_throw_exception_if_url_is_blank(String str) {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfItIsValidURL("",  str));
    }

    @Test
    void should_throw_exception_if_url_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> ValidationService.validateIfItIsValidURL("",  "INVALID"));
    }

}

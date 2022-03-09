package br.com.vayu.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static br.com.vayu.services.ValidationService.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationServiceTest {

    @Test
    void validateIfItIsNull__should_throw_exception_if_object_is_null() {
        assertThrows(IllegalArgumentException.class, () -> validateIfItIsNull("test", null));
    }

    @Test
    void validateIfIsBlankString__should_throw_exception_if_string_is_null() {
        assertThrows(IllegalArgumentException.class, () -> validateIfIsBlankString("",null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void validateIfIsBlankString__should_throw_exception_if_string_is_blank(String str) {
        assertThrows(IllegalArgumentException.class, () -> validateIfIsBlankString("", str));
    }

    @Test
    void validateIfItIsValidCode__should_throw_exception_if_code_is_null() {
        assertThrows(IllegalArgumentException.class, () -> validateIfItIsValidCode(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void validateIfItIsValidCode__should_throw_exception_if_code_is_blank(String str) {
        assertThrows(IllegalArgumentException.class, () -> validateIfItIsValidCode(str));
    }

    @Test
    void validateIfItIsValidCode__should_throw_exception_if_code_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> validateIfItIsValidCode("INVALIDO"));
    }

    @Test
    void validateIfIntIsWithinRange__should_throw_exception_if_int_is_less_than_range_min() {
        assertThrows(IllegalArgumentException.class, () ->
                validateIfIntIsWithinRange("",1, 2, 3));
    }

    @Test
    void validateIfIntIsWithinRange__should_throw_exception_if_int_is_bigger_than_range_max() {
        assertThrows(IllegalArgumentException.class, () ->
                validateIfIntIsWithinRange("",4, 2, 3));
    }

    @Test
    void validateIfIsValidHexColorCode__should_throw_exception_if_hex_color_code_is_null() {
        assertThrows(IllegalArgumentException.class, () -> validateIfIsValidHexColorCode("",null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void validateIfIsValidHexColorCode__should_throw_exception_if_hex_color_code_is_blank(String str) {
        assertThrows(IllegalArgumentException.class, () -> validateIfIsValidHexColorCode("", str));
    }

    @Test
    void validateIfIsValidHexColorCode__should_throw_exception_if_hex_color_code_is_invalid() {
        assertThrows(IllegalArgumentException.class, () ->
                validateIfIsValidHexColorCode("","INVALIDO"));
    }

    @Test
    void validateIfItIsValidURL__should_throw_exception_if_url_is_null() {
        assertThrows(IllegalArgumentException.class, () -> validateIfItIsValidURL("",null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t"})
    void validateIfItIsValidURL__should_throw_exception_if_url_is_blank(String str) {
        assertThrows(IllegalArgumentException.class, () -> validateIfItIsValidURL("", str));
    }

    @Test
    void validateIfItIsValidURL__should_throw_exception_if_url_is_invalid() {
        assertThrows(IllegalArgumentException.class, () -> validateIfItIsValidURL("","INVALID"));
    }

}

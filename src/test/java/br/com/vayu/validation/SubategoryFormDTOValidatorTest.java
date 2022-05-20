package br.com.vayu.validation;

import br.com.vayu.dto.SubcategoryFormDTO;
import br.com.vayu.repositories.SubcategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

class SubategoryFormDTOValidatorTest {

    private SubcategoryFormDTOValidator validator;
    private Errors errors;
    private SubcategoryFormDTO form;

    private final String code = "code";
    private final String name = "name";

    @BeforeEach
    void setUp() {
        SubcategoryRepository repository = mock(SubcategoryRepository.class);
        when(repository.existsByCodeAndIdNot(eq(code), not(eq(1)))).thenReturn(true);
        when(repository.existsByNameAndIdNot(eq(name), not(eq(1)))).thenReturn(true);

        validator = new SubcategoryFormDTOValidator(repository);
        errors = mock(Errors.class);
        form = new SubcategoryFormDTO();
    }

    @Test
    void validate__should_return_error_when_code_is_already_registered() {
        form.setCode(code);

        validator.validate(form, errors);
        verify(errors).rejectValue(code, "code.notunique");
        verifyNoMoreInteractions(errors);
    }

    @Test
    void validate__should_not_return_error_when_code_is_not_registered() {
        validator.validate(form, errors);
        verifyNoInteractions(errors);
    }

    @Test
    void validate__should_not_return_error_when_code_is_unaltered_in_existing_entity() {
        form.setId(1);
        form.setCode(code);

        validator.validate(form, errors);
        verifyNoInteractions(errors);
    }

    @Test
    void validate__should_return_error_when_name_is_already_registered() {
        form.setName(name);

        validator.validate(form, errors);
        verify(errors).rejectValue(name, "name.notunique");
        verifyNoMoreInteractions(errors);
    }

    @Test
    void validate__should_not_return_error_when_name_is_not_registered() {
        validator.validate(form, errors);
        verifyNoInteractions(errors);
    }

    @Test
    void validate__should_not_return_error_when_name_is_unaltered_in_existing_entity() {
        form.setId(1);
        form.setName(name);

        validator.validate(form, errors);
        verifyNoInteractions(errors);
    }

}
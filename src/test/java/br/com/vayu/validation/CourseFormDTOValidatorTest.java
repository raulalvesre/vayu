package br.com.vayu.validation;

import br.com.vayu.dto.CourseFormDTO;
import br.com.vayu.repositories.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

class CourseFormDTOValidatorTest {

    private CourseFormDTOValidator validator;
    private Errors errors;
    private CourseFormDTO form;

    private final String code = "code";
    private final String name = "name";

    @BeforeEach
    void setUp() {
        CourseRepository repository = mock(CourseRepository.class);
        when(repository.existsByCodeAndIdNot(eq(code), not(eq(1)))).thenReturn(true);
        when(repository.existsByNameAndIdNot(eq(name), not(eq(1)))).thenReturn(true);

        validator = new CourseFormDTOValidator(repository);
        errors = mock(Errors.class);
        form = new CourseFormDTO();
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
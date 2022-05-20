package br.com.vayu.validation;

import br.com.vayu.dto.CourseFormDTO;
import br.com.vayu.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CourseFormDTOValidator implements Validator {

    private final CourseRepository courseRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CourseFormDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CourseFormDTO form = (CourseFormDTO) o;

        if (courseRepository.existsByCodeAndIdNot(form.getCode(), form.getId())) {
            errors.rejectValue("code", "code.notunique");
        }

        if (courseRepository.existsByNameAndIdNot(form.getName(), form.getId())) {
            errors.rejectValue("name", "name.notunique");
        }
    }

}

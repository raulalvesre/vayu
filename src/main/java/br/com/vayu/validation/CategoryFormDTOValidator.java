package br.com.vayu.validation;

import br.com.vayu.dto.CategoryFormDTO;
import br.com.vayu.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CategoryFormDTOValidator implements Validator {

    private final CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryFormDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryFormDTO form = (CategoryFormDTO) o;

        if (categoryRepository.existsByCodeAndIdNot(form.getCode(), form.getId())) {
            errors.rejectValue("code", "code.notunique");
        }

        if (categoryRepository.existsByNameAndIdNot(form.getName(), form.getId())) {
            errors.rejectValue("name", "name.notunique");
        }
    }

}

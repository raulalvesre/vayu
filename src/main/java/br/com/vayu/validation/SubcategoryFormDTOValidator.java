package br.com.vayu.validation;

import br.com.vayu.dto.SubcategoryFormDTO;
import br.com.vayu.repositories.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SubcategoryFormDTOValidator implements Validator {

    private final SubcategoryRepository subcategoryRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return SubcategoryFormDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SubcategoryFormDTO form = (SubcategoryFormDTO) o;

        if (subcategoryRepository.existsByCodeAndIdNot(form.getCode(), form.getId())) {
            errors.rejectValue("code", "code.notunique");
        }

        if (subcategoryRepository.existsByNameAndIdNot(form.getName(), form.getId())) {
            errors.rejectValue("name", "name.notunique");
        }
    }

}

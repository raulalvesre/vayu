package br.com.vayu.services;

import br.com.vayu.dto.CompleteCategoryDTO;
import br.com.vayu.dto.CourseDTO;
import br.com.vayu.dto.SubcategoryDTO;
import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import br.com.vayu.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CompleteCategoryDTO> getCompleteActiveCategoryDtoList() {
        var activeCategories = categoryRepository.findAllByActiveTrue();

        return activeCategories.stream()
                .map(ct -> {
                    var subcategories = ct.getSubcategories();

                    var courseDtos = subcategories.stream()
                            .map(Subcategory::getCourses)
                            .flatMap(List::stream)
                            .filter(Course::isVisible)
                            .map(CourseDTO::new)
                            .toList();

                    var subcategoryDtos = subcategories.stream()
                            .map(SubcategoryDTO::new)
                            .toList();

                    return new CompleteCategoryDTO(ct, subcategoryDtos, courseDtos);
                })
                .toList();
    }

}

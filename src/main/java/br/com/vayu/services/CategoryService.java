package br.com.vayu.services;

import br.com.vayu.dto.CompleteCategoryDTO;
import br.com.vayu.dto.CourseDTO;
import br.com.vayu.dto.SubcategoryDTO;
import br.com.vayu.repositories.CategoryRepository;
import br.com.vayu.repositories.CourseRepository;
import br.com.vayu.repositories.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final CourseRepository courseRepository;

    public CategoryService(CategoryRepository categoryRepository,
                           SubcategoryRepository subcategoryRepository,
                           CourseRepository courseRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.courseRepository = courseRepository;
    }

    public List<CompleteCategoryDTO> getCompleteActiveCategoryDtoList() {
        var activeCategories = categoryRepository.findAllByActiveTrue();

        return activeCategories.stream()
                .map(ct -> {
                    var subcategories = subcategoryRepository.findAllByCategoryId(ct.getId());

                    var courseDtos = subcategories.stream()
                            .map(sb -> courseRepository.findAllBySubcategoryId(sb.getId()))
                            .flatMap(List::stream)
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

package br.com.vayu.services;

import br.com.vayu.dto.CategoryDTO;
import br.com.vayu.dto.CategoryFormDTO;
import br.com.vayu.dto.CategoryManagementDTO;
import br.com.vayu.models.Category;
import br.com.vayu.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getActiveCategoryDtoList() {
        var activeCategories = categoryRepository.findAllByActiveTrue();

        return activeCategories.stream()
                .map(CategoryDTO::new)
                .toList();
    }

    public List<CategoryManagementDTO> getCategoryManagementDtoListInOrder() {
        return categoryRepository.findAllByOrderByOrder().stream()
                .map(CategoryManagementDTO::new)
                .toList();
    }

    public CategoryFormDTO getByCode(String categoryCode) {
        var categoryModel = categoryRepository.findByCode(categoryCode);
        return new CategoryFormDTO(categoryModel);
    }

    @Transactional
    public CategoryManagementDTO createCategory(CategoryFormDTO categoryFormDTO) {
        var categoryModel = new Category(categoryFormDTO);
        categoryRepository.save(categoryModel);

        return new CategoryManagementDTO(categoryModel);
    }

    @Transactional
    public CategoryManagementDTO updateCategory(CategoryFormDTO categoryFormDTO) throws Exception {
        if (!categoryRepository.existsById(categoryFormDTO.getId()))
            throw new Exception();

        var updatedCategory = new Category(categoryFormDTO.getId(), categoryFormDTO);

        categoryRepository.save(updatedCategory);

        return new CategoryManagementDTO(updatedCategory);
    }

}

package br.com.vayu.services;

import br.com.vayu.dto.CategoryApiDTO;
import br.com.vayu.dto.CategoryFormDTO;
import br.com.vayu.exceptions.NotFoundException;
import br.com.vayu.models.Category;
import br.com.vayu.projections.CategoryMinifiedProjection;
import br.com.vayu.projections.DashboardCategoryProjection;
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

    public CategoryMinifiedProjection getByCodeMinified(String categoryCode) {
        return categoryRepository.findByCodeMinified(categoryCode)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
    }

    public CategoryFormDTO getByCodeAsFormDto(String categoryCode) {
        return categoryRepository.findByCodeAsFormDto(categoryCode)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
    }

    public List<CategoryApiDTO> getAllActiveCategoryAsApiDtoList() {
        return categoryRepository.findAllByOrderByOrder().stream()
                .map(CategoryApiDTO::new)
                .toList();
    }

    public List<CategoryMinifiedProjection> getMinifiedListInOrder() {
        return categoryRepository.findAllMinifiedInOrder();
    }

    public List<DashboardCategoryProjection> getDashboardProjectionListOrderedDesc() {
        return categoryRepository.findAllOrderedDescAsDashboardCategoryView();
    }

    @Transactional
    public void save(CategoryFormDTO categoryFormDTO) {
        Category categoryModel = new Category(categoryFormDTO);
        categoryRepository.save(categoryModel);
    }

    @Transactional
    public void update(CategoryFormDTO categoryFormDTO) throws Exception {
        Category bdCategory = categoryRepository.findById(categoryFormDTO.getId())
                .orElseThrow(() -> new NotFoundException("Category not found!"));

        bdCategory.merge(categoryFormDTO);

        categoryRepository.save(bdCategory);
    }

    @Transactional
    public void deactivate(int id) {
        categoryRepository.deactivate(id);
    }

}

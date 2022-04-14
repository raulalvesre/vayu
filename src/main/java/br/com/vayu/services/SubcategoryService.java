package br.com.vayu.services;

import br.com.vayu.dto.SubcategoryFormDTO;
import br.com.vayu.exceptions.NotFoundException;
import br.com.vayu.models.Category;
import br.com.vayu.models.Subcategory;
import br.com.vayu.projections.SubcategoryMinifiedProjection;
import br.com.vayu.repositories.CategoryRepository;
import br.com.vayu.repositories.SubcategoryRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    public SubcategoryService(SubcategoryRepository subcategoryRepository,
                              CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<SubcategoryMinifiedProjection> getAllMinifiedByCategoryCode(String categoryCode) {
        return subcategoryRepository.findAllMinifiedByCategoryCode(categoryCode);
    }

    public SubcategoryFormDTO getByCodeAndCategoryCodeAsFormDto(String catetegoryCode,
                                                                String subcategoryCode) {
        return subcategoryRepository.findByCodeAndCategoryCodeAsFormDto(subcategoryCode, catetegoryCode)
                .orElseThrow(() -> new NotFoundException("Subcategory not found!"));
    }

    public SubcategoryMinifiedProjection getByCodeAndCategoryCodeMinified(String catetegoryCode,
                                                           String subcategoryCode) {
        return subcategoryRepository.findByCodeAndCategoryCodeMinified(subcategoryCode, catetegoryCode)
                .orElseThrow(() -> new NotFoundException("Subcategory not found!"));
    }

    @Transactional
    public void save(SubcategoryFormDTO subcategoryFormDTO) {
        Category category = categoryRepository.findByCode(subcategoryFormDTO.getCategoryCode())
                .orElseThrow(() -> new NotFoundException("Category not found!"));

        Subcategory model = new Subcategory(subcategoryFormDTO, category);
        subcategoryRepository.save(model);
    }

    @Transactional
    public void update(SubcategoryFormDTO subcategoryFormDTO) throws Exception {
        Subcategory bdSubcategory = subcategoryRepository.findById(subcategoryFormDTO.getId())
                .orElseThrow(() -> new NotFoundException("Subcategory not found!"));

        Category category = categoryRepository.findByCode(subcategoryFormDTO.getCategoryCode())
                .orElseThrow(() -> new NotFoundException("Category not found!"));

        bdSubcategory.merge(subcategoryFormDTO, category);

        subcategoryRepository.save(bdSubcategory);
    }

    @Modifying
    @Transactional
    public void deactivate(int id) {
        subcategoryRepository.deactivate(id);
    }

}

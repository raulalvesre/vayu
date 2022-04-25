package br.com.vayu.services;

import br.com.vayu.dto.*;
import br.com.vayu.exceptions.NotFoundException;
import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import br.com.vayu.projections.CategoryMinifiedProjection;
import br.com.vayu.projections.DashboardCategoryProjection;
import br.com.vayu.repositories.CategoryRepository;
import br.com.vayu.repositories.CourseRepository;
import br.com.vayu.repositories.SubcategoryRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public CategoryMinifiedProjection getByCodeMinified(String categoryCode) {
        return categoryRepository.findByCodeMinified(categoryCode)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
    }

    public CategoryFormDTO getByCodeAsFormDto(String categoryCode) {
        return categoryRepository.findByCodeAsFormDto(categoryCode)
                .orElseThrow(() -> new NotFoundException("Category not found!"));
    }

    public List<CategoryApiDTO> getAllForCategoryApi() {
        List<Course> courses = courseRepository
                .findAllForCategoryApi();

        if (courses.isEmpty()) {
            return new ArrayList<>();
        }

        LinkedHashMap<Subcategory, List<CourseApiDTO>> subcategoriesAndCoursesDto = courses.stream()
                .collect(Collectors.groupingBy(Course::getSubcategory,
                        LinkedHashMap::new,
                        Collectors.mapping(CourseApiDTO::new, Collectors.toList())));

        LinkedHashMap<Category, List<SubcategoryApiDTO>> categoriesAndSubcategoriesDtos = subcategoriesAndCoursesDto
                .keySet()
                .stream()
                .collect(Collectors.groupingBy(Subcategory::getCategory,
                        LinkedHashMap::new,
                        Collectors.mapping(x -> new SubcategoryApiDTO(x, subcategoriesAndCoursesDto.get(x)),
                                Collectors.toList())));

        return categoriesAndSubcategoriesDtos.keySet().stream()
                .map(x -> new CategoryApiDTO(x, categoriesAndSubcategoriesDtos.get(x)))
                .toList();
    }

    public List<CategoryMinifiedProjection> getAllMinifiedInOrder() {
        return categoryRepository.findAllMinifiedInOrder();
    }

    public List<DashboardCategoryProjection> getAllAsDashboardProjectionOrderedDesc() {
        return categoryRepository.findAllOrderedDescAsDashboardCategoryView();
    }

    public List<CategoryLoginPageDTO> getAllForLoginPage() {
        List<Subcategory> subcategories = subcategoryRepository
                .findDistinctForLoginPage();

        if (subcategories.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Category, List<String>> categoriesAndSubcategoriesNames = subcategories.stream()
                .collect(Collectors.groupingBy(Subcategory::getCategory,
                        LinkedHashMap::new,
                        Collectors.mapping(Subcategory::getName, Collectors.toList())));

        return categoriesAndSubcategoriesNames.keySet().stream()
                .map(k -> new CategoryLoginPageDTO(k, categoriesAndSubcategoriesNames.get(k)))
                .toList();
    }


    public CategoryPublicPageDTO getAllForCategoryPublicPage(String categoryCode) {
        List<Course> courses = courseRepository
                .findAllForCategoryPublicPage(categoryCode);

        if (courses.isEmpty()) {
            Category category = categoryRepository.findByCode(categoryCode)
                    .orElseThrow(() -> new NotFoundException("Category not found!"));

            return new CategoryPublicPageDTO(category, new ArrayList<>());
        }

        LinkedHashMap<Subcategory, List<CourseCategoryPublicPageDTO>> subcategoriesAndCoursesDto = courses.stream()
                .collect(Collectors.groupingBy(Course::getSubcategory,
                        LinkedHashMap::new,
                        Collectors.mapping(CourseCategoryPublicPageDTO::new, Collectors.toList())));

        Category category = subcategoriesAndCoursesDto.keySet().iterator().next().getCategory();

        List<SubcategoryCategoryPublicPageDTO> subcategoriesDtos = subcategoriesAndCoursesDto.keySet().stream()
                .map(k -> new SubcategoryCategoryPublicPageDTO(k, subcategoriesAndCoursesDto.get(k)))
                .toList();

        return new CategoryPublicPageDTO(category, subcategoriesDtos);
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

    @Modifying
    @Transactional
    public void deactivate(int id) {
        categoryRepository.deactivate(id);
    }

}

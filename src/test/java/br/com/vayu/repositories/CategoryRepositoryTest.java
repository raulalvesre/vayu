package br.com.vayu.repositories;

import br.com.vayu.builders.CategoryBuilder;
import br.com.vayu.dto.CategoryFormDTO;
import br.com.vayu.models.Category;
import br.com.vayu.projections.CategoryMinifiedProjection;
import br.com.vayu.projections.DashboardCategoryProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    private  CategoryRepository categoryRepository;

    @Autowired
    private  TestEntityManager em;

    private final String code = "code";
    private final String name = "name";
    private final String description = "description";
    private final String studyGuide = "studyGuide";
    private final String iconPath = "iconPath";
    private final String colorCode = "#FFF";

    @Test
    void findByCodeMinified__should_return_category_as_minified_projection() {
        createAndSaveCategory();

        Optional<CategoryMinifiedProjection> bdCategoryOptional = categoryRepository.findByCodeMinified("code");
        assertTrue(bdCategoryOptional.isPresent());

        CategoryMinifiedProjection bdCategory = bdCategoryOptional.get();

        assertEquals(name, bdCategory.getName());
        assertEquals(code, bdCategory.getCode());
        assertTrue(bdCategory.isActive());
    }

    @Test
    void findByCodeAsFormDto__should_return_category_as_form_dto() {
        createAndSaveCategory();

        Optional<CategoryFormDTO> bdCategoryOptional = categoryRepository.findByCodeAsFormDto("code");
        assertTrue(bdCategoryOptional.isPresent());

        CategoryFormDTO bdCategory = bdCategoryOptional.get();

        assertEquals(name, bdCategory.getName());
        assertEquals(code, bdCategory.getCode());
        assertEquals(description, bdCategory.getDescription());
        assertEquals(studyGuide, bdCategory.getStudyGuide());
        assertTrue(bdCategory.isActive());
        assertEquals(0, bdCategory.getOrder());
        assertEquals(iconPath, bdCategory.getIconPath());
        assertEquals(colorCode, bdCategory.getColorCode());
    }

    @Test
    void findAllMinifiedInOrder__should_return_all_minified() {
        createAndSaveCategory("name2", 2);
        createAndSaveCategory("name1", 1);

        List<CategoryMinifiedProjection> bdCategories = categoryRepository.findAllMinifiedInOrder();

        assertEquals(2, bdCategories.size());
        assertEquals("name1", bdCategories.get(0).getName());
        assertEquals("name2", bdCategories.get(1).getName());
        assertEquals(code, bdCategories.get(0).getCode());
        assertEquals(code, bdCategories.get(1).getCode());
        assertTrue(bdCategories.get(0).isActive());
        assertTrue(bdCategories.get(1).isActive());;
    }

    @Test
    void findAllOrderedDescAsDashboardCategoryView__should_return_all_as_dashboard_category_view() {
        createAndSaveCategory();

        List<DashboardCategoryProjection> bdCategories = categoryRepository.findAllOrderedDescAsDashboardCategoryView();

        assertEquals(1, bdCategories.size());
        assertEquals(name, bdCategories.get(0).getName());
        assertEquals(0, bdCategories.get(0).getTotalCourses());
    }

    @Test
    void deactivate__should_deactivate_category() {
        Category category = createAndSaveCategory();

        categoryRepository.deactivate(category.getId());

        Optional<Category> bdCategoryOptional = categoryRepository.findById(category.getId());

        assertTrue(bdCategoryOptional.isPresent());
        assertFalse(bdCategoryOptional.get().isActive());
    }

    private Category createAndSaveCategory() {
        Category category = new CategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(0)
                .iconPath(iconPath)
                .colorCode(colorCode)
                .build();

        return em.persist(category);
    }

    private Category createAndSaveCategory(String name, int order) {
        Category category = new CategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(order)
                .iconPath(iconPath)
                .colorCode(colorCode)
                .build();

        return em.persist(category);
    }

}
package br.com.vayu.repositories;

import br.com.vayu.builders.CategoryBuilder;
import br.com.vayu.builders.CourseBuilder;
import br.com.vayu.builders.SubcategoryBuilder;
import br.com.vayu.dto.SubcategoryFormDTO;
import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import br.com.vayu.projections.SubcategoryMinifiedProjection;
import org.junit.jupiter.api.BeforeEach;
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
class SubcategoryRepositoryTest {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Autowired
    private TestEntityManager em;

    private Category activeCategory;

    private final String code = "code";
    private final String name = "name";
    private final String description = "description";
    private final String studyGuide = "studyGuide";

    @BeforeEach
    void beforeAll() {
        this.activeCategory = createAndSaveCategory(true);

        em.persist(activeCategory);
    }

    @Test
    void findByCodeAndCategoryCodeAsFormDto__should_return_as_form_dto_by_code_and_category_code() {
        createAndSaveSubcategory();

        em.flush();
        em.clear();

        Optional<SubcategoryFormDTO> bdSubcategoryOptional = subcategoryRepository
                .findByCodeAndCategoryCodeAsFormDto("code", "code");
        assertTrue(bdSubcategoryOptional.isPresent());

        SubcategoryFormDTO bdSubcategory = bdSubcategoryOptional.get();

        assertEquals(name, bdSubcategory.getName());
        assertEquals(code, bdSubcategory.getCode());
        assertEquals(description, bdSubcategory.getDescription());
        assertEquals(studyGuide, bdSubcategory.getStudyGuide());
        assertTrue(bdSubcategory.isActive());
        assertEquals(0, bdSubcategory.getOrder());
        assertEquals(activeCategory.getCode(), bdSubcategory.getCategoryCode());
    }

    @Test
    void findByCodeAndCategoryCodeMinified__should_return_minified_by_code_and_category_code() {
        createAndSaveSubcategory();

        em.flush();
        em.clear();

        Optional<SubcategoryMinifiedProjection> bdSubcategoryOptional = subcategoryRepository
                .findByCodeAndCategoryCodeMinified("code", "code");
        assertTrue(bdSubcategoryOptional.isPresent());

        SubcategoryMinifiedProjection bdSubcategory = bdSubcategoryOptional.get();

        assertEquals(name, bdSubcategory.getName());
        assertEquals(code, bdSubcategory.getCode());
        assertTrue(bdSubcategory.isActive());
    }

    @Test
    void findAllMinified_should_return_all_minified() {
        createAndSaveSubcategory();
        createAndSaveSubcategory();

        em.flush();
        em.clear();

        List<SubcategoryMinifiedProjection> bdSubcategories = subcategoryRepository.findAllMinified();

        assertEquals(2, bdSubcategories.size());
        assertEquals(name, bdSubcategories.get(0).getName());
        assertEquals(name, bdSubcategories.get(1).getName());
        assertEquals(code, bdSubcategories.get(0).getCode());
        assertEquals(code, bdSubcategories.get(1).getCode());
        assertTrue(bdSubcategories.get(0).isActive());
        assertTrue(bdSubcategories.get(1).isActive());
    }

    @Test
    void findAllMinifiedInAlphabeticOrder_should_return_minified_in_alphabetic_order() {
        createAndSaveSubcategory("name2");
        createAndSaveSubcategory("name1");

        em.flush();
        em.clear();

        List<SubcategoryMinifiedProjection> bdSubcategories = subcategoryRepository.findAllMinifiedInAlphabeticOrder();

        assertEquals(2, bdSubcategories.size());
        assertEquals("name1", bdSubcategories.get(0).getName());
        assertEquals("name2", bdSubcategories.get(1).getName());
        assertEquals(code, bdSubcategories.get(0).getCode());
        assertEquals(code, bdSubcategories.get(1).getCode());
        assertTrue(bdSubcategories.get(0).isActive());
        assertTrue(bdSubcategories.get(1).isActive());
    }

    @Test
    void findAllMinifiedByCategoryCode__should_return_all_minified_by_category_code() {
        createAndSaveSubcategory();
        createAndSaveSubcategory();

        em.flush();
        em.clear();

        List<SubcategoryMinifiedProjection> bdSubcategories =
                subcategoryRepository.findAllMinifiedByCategoryCode("code");

        assertEquals(2, bdSubcategories.size());
        assertEquals(name, bdSubcategories.get(0).getName());
        assertEquals(name, bdSubcategories.get(1).getName());
        assertEquals(code, bdSubcategories.get(0).getCode());
        assertEquals(code, bdSubcategories.get(1).getCode());
        assertTrue(bdSubcategories.get(0).isActive());
        assertTrue(bdSubcategories.get(1).isActive());
    }

    @Test
    void findAllMinifiedByCategoryCode__should_return_empty_list_with_wrong_category_code() {
        Category category2 = new CategoryBuilder()
                .code(code+2)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(0)
                .iconPath("iconPath")
                .colorCode("#FFF")
                .build();

        em.persist(category2);

        createAndSaveSubcategory(category2);

        em.flush();
        em.clear();

        List<SubcategoryMinifiedProjection> bdSubcategories =
                subcategoryRepository.findAllMinifiedByCategoryCode("code");

        assertTrue(bdSubcategories.isEmpty());
    }

    @Test
    void findDistinctForLoginPage__should_return_active_subcategories_with_active_category_and_any_visible_course() {
        Subcategory subcategory1 = createAndSaveSubcategory(activeCategory);
        Subcategory subcategory2 = createAndSaveSubcategory(activeCategory);
        createAndSaveCourse(subcategory1);
        createAndSaveCourse(subcategory2);

        em.flush();
        em.clear();

        List<Subcategory> bdSubcategories = subcategoryRepository.findDistinctForLoginPage();

        assertEquals(2, bdSubcategories.size());
        assertEquals(subcategory1, bdSubcategories.get(0));
        assertEquals(subcategory2, bdSubcategories.get(1));
    }

    @Test
    void findDistinctForLoginPage__should_return_active_subcategories_ordered_by_category_order_asc() {
        Category category = new CategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(99)
                .iconPath("iconPath")
                .colorCode("#FFF")
                .build();

        em.persist(category);

        Subcategory subcategory99 = new SubcategoryBuilder()
                .code(code)
                .name(name+99)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(0)
                .category(category)
                .build();

        Subcategory subcategory0 = new SubcategoryBuilder()
                .code(code)
                .name(name+0)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(0)
                .category(activeCategory)
                .build();

        em.persist(subcategory99);
        em.persist(subcategory0);

        createAndSaveCourse(subcategory99);
        createAndSaveCourse(subcategory0);

        em.flush();
        em.clear();

        List<Subcategory> bdSubcategories = subcategoryRepository.findDistinctForLoginPage();

        assertEquals(2, bdSubcategories.size());
        assertEquals(subcategory0, bdSubcategories.get(0));
        assertEquals(subcategory99, bdSubcategories.get(1));
    }

    @Test
    void findDistinctForLoginPage__should_return_active_subcategories_ordered_by_subcategory_order_asc() {
        Subcategory subcategory99 = new SubcategoryBuilder()
                .code(code)
                .name(name+99)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(99)
                .category(activeCategory)
                .build();

        Subcategory subcategory0 = new SubcategoryBuilder()
                .code(code)
                .name(name+0)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(0)
                .category(activeCategory)
                .build();

        em.persist(subcategory99);
        em.persist(subcategory0);

        createAndSaveCourse(subcategory99);
        createAndSaveCourse(subcategory0);

        em.flush();
        em.clear();

        List<Subcategory> bdSubcategories = subcategoryRepository.findDistinctForLoginPage();

        assertEquals(2, bdSubcategories.size());
        assertEquals(subcategory0, bdSubcategories.get(0));
        assertEquals(subcategory99, bdSubcategories.get(1));
    }

    @Test
    void findDistinctForLoginPage__should_return_empty_list_when_category_inactive() {
        Category inactiveCategory = createAndSaveCategory(false);
        Subcategory subcategory = createAndSaveSubcategory(inactiveCategory);
        createAndSaveCourse(subcategory);

        em.flush();
        em.clear();

        List<Subcategory> bdSubcategories = subcategoryRepository.findDistinctForLoginPage();
        assertTrue(bdSubcategories.isEmpty());
    }

    @Test
    void findDistinctForLoginPage__should_return_empty_list_when_subcategory_inactive() {
        Subcategory subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(false)
                .order(0)
                .category(activeCategory)
                .build();

        em.persist(subcategory);

        createAndSaveCourse(subcategory);

        em.flush();
        em.clear();

        List<Subcategory> bdSubcategories = subcategoryRepository.findDistinctForLoginPage();
        assertTrue(bdSubcategories.isEmpty());
    }

    @Test
    void findDistinctForLoginPage__should_return_empty_list_when_subcategory_has_no_visible_course() {
        Subcategory subcategory = createAndSaveSubcategory(activeCategory);

        Course course = new CourseBuilder()
                .code(code)
                .name(name)
                .estimatedHoursToFinish(1)
                .visible(false)
                .targetAudience("targetAudience")
                .instructorName("instructorName")
                .syllabus("syllabus")
                .developedAbilities("developedAbilities")
                .subcategory(subcategory)
                .build();

        em.persist(course);

        em.flush();

        List<Subcategory> bdSubcategories = subcategoryRepository.findDistinctForLoginPage();
        assertTrue(bdSubcategories.isEmpty());
    }

    @Test
    void deactivate__should_deactivate_subcategory() {
        Subcategory subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(1)
                .category(activeCategory)
                .build();

        em.persist(subcategory);
        em.clear();

        subcategoryRepository.deactivate(subcategory.getId());

        Optional<Subcategory> bdSubcategoryOptional = subcategoryRepository.findById(subcategory.getId());

        assertTrue(bdSubcategoryOptional.isPresent());
        assertFalse(bdSubcategoryOptional.get().isActive());
    }

    private Subcategory createAndSaveSubcategory() {
        Subcategory subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(0)
                .category(activeCategory)
                .build();

        return em.persist(subcategory);
    }

    private Subcategory createAndSaveSubcategory(String name) {
        Subcategory subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(0)
                .category(activeCategory)
                .build();

        return em.persist(subcategory);
    }

    private Subcategory createAndSaveSubcategory(Category category) {
        Subcategory subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(0)
                .category(category)
                .build();

        return em.persist(subcategory);
    }

    private Category createAndSaveCategory(boolean active) {
        Category category = new CategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(active)
                .order(0)
                .iconPath("iconPath")
                .colorCode("#FFF")
                .build();

        return em.persist(category);
    }

    private Course createAndSaveCourse(Subcategory subcategory) {
        Course course = new CourseBuilder()
                .code(code)
                .name(name)
                .estimatedHoursToFinish(1)
                .visible(true)
                .targetAudience("targetAudience")
                .instructorName("instructorName")
                .syllabus("syllabus")
                .developedAbilities("developedAbilities")
                .subcategory(subcategory)
                .build();

        return em.persist(course);
    }

}
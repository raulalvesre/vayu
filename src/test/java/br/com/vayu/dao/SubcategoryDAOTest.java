package br.com.vayu.dao;

import br.com.vayu.TestJPAUtil;
import br.com.vayu.builders.CategoryBuilder;
import br.com.vayu.builders.SubcategoryBuilder;
import br.com.vayu.models.Category;
import br.com.vayu.models.Subcategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SubcategoryDAOTest {

    private EntityManager entityManager;
    private SubcategoryDAO subcategoryDAO;
    private Category category;

    private final String code = "code";
    private final String name = "name";
    private final String description = "desc";
    private final String studyGuide = "study guide";

    @BeforeEach
    void beforeEach() {
        entityManager = TestJPAUtil.getEntityManager();
        subcategoryDAO = new SubcategoryDAO(entityManager);
        entityManager.getTransaction().begin();

        category = new CategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(1)
                .iconPath("icon path")
                .colorCode("#FFFFFF")
                .build();

        entityManager.persist(category);
    }

    @AfterEach
    void afterEach() {
        entityManager.getTransaction().rollback();
    }

    @Test
    void findByIdIncludeCategory_should_return_subcategory_with_category() {
        Subcategory subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(1)
                .category(category)
                .build();

        entityManager.persist(subcategory);

        Subcategory bdSubcategory = subcategoryDAO.findByIdJoinFetchCategory(subcategory.getId());

        assertNotNull(bdSubcategory);
        assertEquals(bdSubcategory, subcategory);
    }

    @Test
    void findAllByActiveTrueInOrder_should_return_active_subcategories_in_order() {
        Subcategory subcategory1 = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(1)
                .category(category)
                .build();

        Subcategory subcategory2 = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(2)
                .category(category)
                .build();

        Subcategory subcategory3 = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(false)
                .order(3)
                .category(category)
                .build();

        entityManager.persist(subcategory1);
        entityManager.persist(subcategory2);
        entityManager.persist(subcategory3);

        List<Subcategory> receivedList = subcategoryDAO.findAllByActiveTrueInOrder();

        assertNotNull(receivedList);
        assertEquals(receivedList.size(), 2);
        assertEquals(receivedList.get(0), subcategory1);
        assertEquals(receivedList.get(1), subcategory2);
    }

}

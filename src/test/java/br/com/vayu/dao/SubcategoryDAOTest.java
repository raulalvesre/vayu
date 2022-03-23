package br.com.vayu.dao;

import br.com.vayu.TestJPAUtil;
import br.com.vayu.builders.CategoryBuilder;
import br.com.vayu.builders.SubcategoryBuilder;
import br.com.vayu.models.Category;
import br.com.vayu.models.Subcategory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubcategoryDAOTest {

    private static CategoryDAO categoryDAO;
    private static SubcategoryDAO subcategoryDAO;
    private static Category category;

    private static final String code = "code";
    private static final String name = "name";
    private static final String description = "desc";
    private static final String studyGuide = "study guide";

    @BeforeAll
    static void beforeAll() {
        EntityManager entityManager = TestJPAUtil.getEntityManager();
        subcategoryDAO = new SubcategoryDAO(entityManager);
        categoryDAO = new CategoryDAO(entityManager);

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

        categoryDAO.create(category);
    }

    @AfterEach
    void afterEach() {
        subcategoryDAO.deleteAll();
    }

    @AfterAll
    static void afterAll() {
        categoryDAO.deleteAll();
    }

    @Test
    void findByIdJoinFetchCategory__should_return_subcategory_with_category() {
        Subcategory subcategory = new SubcategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(1)
                .category(category)
                .build();

        subcategoryDAO.create(subcategory);

        Subcategory bdSubcategory = subcategoryDAO.findByIdJoinFetchCategory(subcategory.getId());

        assertNotNull(bdSubcategory);
        assertEquals(bdSubcategory, subcategory);
    }

    @Test
    void findByIdJoinFetchCategory__should_throw_exception() {
        assertThrows(NoResultException.class,
                () -> subcategoryDAO.findByIdJoinFetchCategory(1));
    }

    @Test
    void findAllByActiveTrueInOrder__should_return_active_subcategories_in_order() {
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

        subcategoryDAO.create(subcategory1);
        subcategoryDAO.create(subcategory2);
        subcategoryDAO.create(subcategory3);

        List<Subcategory> receivedList = subcategoryDAO.findAllByActiveTrueInOrder();

        assertNotNull(receivedList);
        assertEquals(2, receivedList.size());
        assertEquals(receivedList.get(0), subcategory1);
        assertEquals(receivedList.get(1), subcategory2);
    }

    @Test
    void findAllByActiveTrueInOrder__should_return_empty_list() {
        List<Subcategory> receivedList = subcategoryDAO.findAllByActiveTrueInOrder();

        assertNotNull(receivedList);
        assertEquals(0, receivedList.size());
    }

}

package br.com.vayu.dao;

import br.com.vayu.TestJPAUtil;
import br.com.vayu.builders.CategoryBuilder;
import br.com.vayu.models.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryDAOTest {

    private EntityManager entityManager;
    private CategoryDAO categoryDAO;

    private final String code = "code";
    private final String name = "name";
    private final String description = "desc";
    private final String studyGuide = "study guide";
    private final String iconPath = "icon path";
    private final String colorCode = "#FFFFFF";

    @BeforeEach
    public void beforeEach() {
        entityManager = TestJPAUtil.getEntityManager();
        categoryDAO = new CategoryDAO(entityManager);
        entityManager.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        entityManager.getTransaction().rollback();
    }

    @Test
    void findAllByActiveTrueInOrder_should_return_active_categories_in_order() {
        Category category1 = new CategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(1)
                .iconPath(iconPath)
                .colorCode(colorCode)
                .build();

        Category category2 = new CategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(true)
                .order(2)
                .iconPath(iconPath)
                .colorCode(colorCode)
                .build();

        Category category3 = new CategoryBuilder()
                .code(code)
                .name(name)
                .description(description)
                .studyGuide(studyGuide)
                .active(false)
                .order(3)
                .iconPath(iconPath)
                .colorCode(colorCode)
                .build();

        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.persist(category3);

        List<Category> expectedList = List.of(category1, category2);
        List<Category> receivedList = categoryDAO.findAllByActiveTrueInOrder();

        assertNotNull(receivedList);
        assertEquals(receivedList.size(), 2);
        assertEquals(receivedList, expectedList);
    }

}

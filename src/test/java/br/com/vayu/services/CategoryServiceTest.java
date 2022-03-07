package br.com.vayu.services;

import br.com.vayu.models.Category;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceTest {

    @Test
    void should_return_list_identical_to_csv_data() {
        List<Category> receivedList = CategoryService.getCategoriesListFromCsv("category/valid_categories.csv");

        Category category1 = new Category("code1",
                "name1",
                "desc1",
                null,
                true,
                1,
                "https://www.test.com.br/icon1.png",
                "#000000");

        Category category2 = new Category("code2",
                "name2",
                "desc2",
                null,
                false,
                2,
                "https://www.test.com.br/icon2.png",
                "#FFFFFF");

        List<Category> expectedList = List.of(category1, category2);

        assertNotNull(receivedList);
        assertEquals(receivedList.size(), 2);
        assertEquals(receivedList, expectedList);
    }

    @Test
    void should_return_empty_list() {
        List<Category> receivedList = CategoryService.getCategoriesListFromCsv("category/empty_categories.csv");

        assertNotNull(receivedList);
        assertEquals(receivedList.size(), 0);
    }

    @Test
    void should_throw_exception_if_csv_does_not_exist() {
        assertThrows(IllegalArgumentException.class,
                () -> CategoryService.getCategoriesListFromCsv("I dont exist"));
    }

    @Test
    void should_throw_exception_if_csv_contains_empty_name() {
        assertThrows(IllegalArgumentException.class,
                () -> CategoryService.getCategoriesListFromCsv("category/empty_name_category.csv"));
    }

    @Test
    void should_throw_exception_if_csv_contains_empty_code() {
        assertThrows(IllegalArgumentException.class,
                () -> CategoryService.getCategoriesListFromCsv("category/empty_code_category.csv"));
    }

    @Test
    void should_throw_exception_if_csv_contains_invalid_code() {
        assertThrows(IllegalArgumentException.class,
                () -> CategoryService.getCategoriesListFromCsv("category/invalid_code_category.csv"));
    }

    @Test
    void should_throw_exception_if_csv_contains_empty_color_code() {
        assertThrows(IllegalArgumentException.class,
                () -> CategoryService.getCategoriesListFromCsv("category/empty_color_code_category.csv"));
    }

    @Test
    void should_throw_exception_if_csv_contains_invalid_color_code() {
        assertThrows(IllegalArgumentException.class,
                () -> CategoryService.getCategoriesListFromCsv("category/invalid_color_code_category.csv"));
    }

    @Test
    void should_print_active_categories() {
        List<Category> receivedList = CategoryService.getCategoriesListFromCsv("category/valid_categories.csv");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CategoryService.printActiveCategories(receivedList);

        String expectedOutput = """
                ACTIVE CATEGORIES:
                Category{code='code1', name='name1', description='desc1', studyGuide='null', active=true, order=1, iconPath='https://www.test.com.br/icon1.png', colorCode='#000000'}
                """;

        assertEquals(outContent.toString(), expectedOutput);
    }

}

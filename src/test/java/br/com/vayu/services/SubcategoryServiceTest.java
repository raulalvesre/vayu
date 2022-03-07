package br.com.vayu.services;

import br.com.vayu.models.Category;
import br.com.vayu.models.Subcategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubcategoryServiceTest {

    private static List<Category> categories;

    @BeforeAll
    static void initialize_categories_list() {
        categories = CategoryService.getCategoriesListFromCsv("category/valid_categories.csv");
    }

    @Test
    void should_return_list_identical_to_csv_data() {
        List<Subcategory> receivedList = SubcategoryService.getSubcategoriesListFromCsv(
                "subcategory/valid_subcategories.csv",
                categories);

        Subcategory subcategory1 = new Subcategory("code1",
                "name1",
                "desc1",
                null,
                true,
                1,
                CategoryService.getCategoryByCode(categories, "code1"));

        Subcategory subcategory2 = new Subcategory("code2",
                "name2",
                "",
                null,
                false,
                2,
                CategoryService.getCategoryByCode(categories, "code2"));

        List<Subcategory> expectedList = List.of(subcategory1, subcategory2);

        assertNotNull(receivedList);
        assertEquals(receivedList.size(), 2);
        assertEquals(receivedList, expectedList);
    }

    @Test
    void should_return_empty_list() {
        List<Subcategory> receivedList = SubcategoryService.getSubcategoriesListFromCsv(
                "subcategory/empty_subcategories.csv",
                categories);

        assertNotNull(receivedList);
        assertEquals(receivedList.size(), 0);
    }

    @Test
    void should_throw_exception_if_csv_does_not_exist() {
        assertThrows(IllegalArgumentException.class,
                () -> SubcategoryService.getSubcategoriesListFromCsv("I dont exist", categories));
    }

    @Test
    void should_throw_exception_if_csv_contains_empty_name() {
        assertThrows(IllegalArgumentException.class,
                () -> SubcategoryService.getSubcategoriesListFromCsv(
                        "subcategory/empty_name_subcategory.csv", categories));
    }

    @Test
    void should_throw_exception_if_csv_contains_empty_code() {
        assertThrows(IllegalArgumentException.class,
                () -> SubcategoryService.getSubcategoriesListFromCsv(
                        "subcategory/empty_code_subcategory.csv", categories));
        ;
    }

    @Test
    void should_throw_exception_if_csv_contains_invalid_code() {
        assertThrows(IllegalArgumentException.class,
                () -> SubcategoryService.getSubcategoriesListFromCsv(
                        "subcategory/invalid_code_subcategory.csv", categories));
        ;
    }

    @Test
    void should_throw_exception_if_csv_contains_empty_category_code() {
        assertThrows(IllegalArgumentException.class,
                () -> SubcategoryService.getSubcategoriesListFromCsv(
                        "subcategory/empty_category_code_subcategory.csv", categories));
        ;
    }

    @Test
    void should_throw_exception_if_csv_contains_invalid_color_code() {
        assertThrows(IllegalArgumentException.class,
                () -> SubcategoryService.getSubcategoriesListFromCsv(
                        "subcategory/invalid_category_code_subcategory.csv", categories));
        ;
    }

    @Test
    void should_print_sub_categories_with_no_description() {
        List<Subcategory> receivedList = SubcategoryService.getSubcategoriesListFromCsv(
                "subcategory/valid_subcategories.csv", categories);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SubcategoryService.printSubCategoriesWithNoDescription(receivedList);

        String expectedOutput = """
                SUB CATEGORIES WITHOUT DESCRIPTION
                SubCategory{code='code2', name='name2', description='', studyGuide='null', active=false, order=2, category=name2}
                """;

        assertEquals(outContent.toString(), expectedOutput);
    }

    @Test
    void should_print_quantity_of_active_sub_categories_with_description() {
        List<Subcategory> receivedList = SubcategoryService.getSubcategoriesListFromCsv(
                "subcategory/valid_subcategories.csv", categories);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SubcategoryService.printQuantityOfActiveSubCategoriesWithDescription(receivedList);

        String expectedOutput = "QUANTITY OF SUBCATEGORIES WITH DESCRIPTION: 1\n";

        assertEquals(outContent.toString(), expectedOutput);
    }

}

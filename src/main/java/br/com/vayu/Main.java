package br.com.vayu;

import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import br.com.vayu.services.CategoryService;
import br.com.vayu.services.CourseService;
import br.com.vayu.services.HtmlCreatorService;
import br.com.vayu.services.SubcategoryService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<Category> categories = CategoryService.getCategoriesListFromCsv("categories.csv");
        List<Subcategory> subCategories = SubcategoryService.getSubcategoriesListFromCsv("subcategories.csv", categories);
        List<Course> courses = CourseService.getCourseListFromCsv("courses.csv", subCategories);

        HtmlCreatorService.generateCategoriesHtml(
                categories,
                subCategories,
                courses);

        System.out.println();
        categories.forEach(System.out::println);
        System.out.println();
        subCategories.forEach(System.out::println);
        System.out.println();
        courses.forEach(System.out::println);

        System.out.println();
        CategoryService.printActiveCategories(categories);

        System.out.println();
        SubcategoryService.printSubCategoriesWithNoDescription(subCategories);

        System.out.println();
        CourseService.printIfThereIsPrivateCourse(courses);

        System.out.println();
        CourseService.printInstructorNames(courses);

        System.out.println();
        SubcategoryService.printQuantityOfActiveSubCategoriesWithDescription(subCategories);

        System.out.println();
        CourseService.printInstructorNameAndHowManyCoursesTheyHave(courses);
    }

}

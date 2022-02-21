package br.com.vayu.services;

import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.SubCategory;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CsvParserService {

    public static Map<String, Category> getCategoriesMapFromCsv() {
        Map<String, Category> categories = new HashMap<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream categoriesInputStream = classLoader.getResourceAsStream("categories.csv");

        try (Scanner lineScanner = new Scanner(categoriesInputStream, StandardCharsets.UTF_8)) {
            lineScanner.nextLine(); // skip first .csv line

            while (lineScanner.hasNext()) {
                Scanner columnScanner = new Scanner(lineScanner.nextLine());
                columnScanner.useDelimiter(",");

                String name = columnScanner.next().trim();
                String code = columnScanner.next().trim();
                String orderStr = columnScanner.next();
                String description = columnScanner.next().trim();
                String activeStr = columnScanner.next().trim();
                String iconPath = columnScanner.next().trim();
                String colorCode = columnScanner.hasNext() ? columnScanner.next().trim() : null;

                int order = orderStr.isBlank() ? 0 : Integer.parseInt(orderStr);
                boolean active = activeStr.equals("ATIVA");

                Category category = new Category(
                        code,
                        name,
                        description,
                        null,
                        active,
                        order,
                        iconPath,
                        colorCode);

                categories.put(code, category);
            }
        }

        return categories;
    }

    public static Map<String, SubCategory> getSubCategoriesMapFromCsv(Map<String, Category> categoryMap) {
        Map<String, SubCategory> subCategories = new HashMap<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream subCategoriesInputStream = classLoader.getResourceAsStream("subcategories.csv");

        try (Scanner lineScanner = new Scanner(subCategoriesInputStream, StandardCharsets.UTF_8)) {
            lineScanner.nextLine(); // skip first .csv line

            while (lineScanner.hasNext()) {
                Scanner columnScanner = new Scanner(lineScanner.nextLine());
                columnScanner.useDelimiter(",");

                String name = columnScanner.next().trim();
                String code = columnScanner.next().trim();
                String orderStr = columnScanner.next();
                String description = columnScanner.next().trim();
                String activeStr = columnScanner.next().trim();
                String categoryCode = columnScanner.hasNext() ? columnScanner.next().trim() : null;

                if (categoryCode == null) {
                    System.out.printf("Sub Category[%s] with invalid Category code could not be created. " +
                            "Please check the .csv!", code);
                    continue;
                }

                int order = orderStr.isBlank() ? 0 : Integer.parseInt(orderStr);
                boolean active = activeStr.equals("ATIVA");
                Category category = categoryMap.get(categoryCode);

                SubCategory subCategory = new SubCategory(
                        code,
                        name,
                        description,
                        null,
                        active,
                        order,
                        category);

                subCategories.put(code, subCategory);
            }
        }

        return subCategories;
    }

    public static Map<String, Course> getCoursesMapFromCsv(Map<String, SubCategory> subCategoryMap) {
        Map<String, Course> courses = new HashMap<>();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream coursesInputStream = classLoader.getResourceAsStream("courses.csv");

        try (Scanner lineScanner = new Scanner(coursesInputStream, StandardCharsets.UTF_8)) {
            lineScanner.nextLine(); // skip first .csv line

            while (lineScanner.hasNext()) {
                Scanner columnScanner = new Scanner(lineScanner.nextLine());
                columnScanner.useDelimiter(",");

                String name = columnScanner.next().trim();
                String code = columnScanner.next().trim();
                String estimatedHoursToFinishStr = columnScanner.next();
                String visibleStr = columnScanner.next();
                String targetAudience = columnScanner.next().trim();
                String instructorName = columnScanner.next().trim();
                String syllabus = columnScanner.next().trim();
                String developedAbilities = columnScanner.next().trim();
                String subCategoryCode = columnScanner.hasNext() ? columnScanner.next().trim() : null;

                if (subCategoryCode == null) {
                    System.out.printf("Course[%s] with invalid Sub Category's code could not be created." +
                            " Please check the .csv!%n", code);
                    continue;
                }

                int estimatedHoursToFinish = estimatedHoursToFinishStr.isBlank() ? 0
                        : Integer.parseInt(estimatedHoursToFinishStr);
                boolean visible = visibleStr.equals("PÚBLICA");
                SubCategory subCategory = subCategoryMap.get(subCategoryCode);

                Course course = new Course(
                        code,
                        name,
                        estimatedHoursToFinish,
                        visible,
                        targetAudience,
                        instructorName,
                        syllabus,
                        developedAbilities,
                        subCategory);

                courses.put(code, course);
            }
        }

        return courses;
    }

}

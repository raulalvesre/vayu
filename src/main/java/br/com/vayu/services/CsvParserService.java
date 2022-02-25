package br.com.vayu.services;

import br.com.vayu.models.Category;
import br.com.vayu.models.Course;
import br.com.vayu.models.SubCategory;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CsvParserService {

    public static List<Category> getCategoriesMapFromCsv() {
        List<Category> categories = new ArrayList<>();

        InputStream categoriesInputStream = getInputStreamFromResources("categories.csv");

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
                String colorCode = columnScanner.next().trim();

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

                categories.add(category);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid categories.csv, please check!!!");
        }

        return categories;
    }

    private static InputStream getInputStreamFromResources(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    public static List<SubCategory> getSubCategoriesMapFromCsv(List<Category> categoryList) {
        List<SubCategory> subCategories = new ArrayList<>();

        InputStream subCategoriesInputStream = getInputStreamFromResources("subcategories.csv");

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
                String categoryCode = columnScanner.next().trim();

                int order = orderStr.isBlank() ? 0 : Integer.parseInt(orderStr);
                boolean active = activeStr.equals("ATIVA");
                Category category = getCategoryByCode(categoryList, categoryCode);

                SubCategory subCategory = new SubCategory(
                        code,
                        name,
                        description,
                        null,
                        active,
                        order,
                        category);

                subCategories.add(subCategory);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid subcategories.csv, please check!!!");
        }

        return subCategories;
    }

    public static Category getCategoryByCode(List<Category> categories, String categoryCode) {
        return categories.stream()
                .filter(x -> x.getCode().equals(categoryCode))
                .findAny()
                .orElse(null);
    }

    public static List<Course> getCoursesMapFromCsv(List<SubCategory> subCategoryList) {
        List<Course> courses = new ArrayList<>();

        InputStream coursesInputStream = getInputStreamFromResources("courses.csv");

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
                String subCategoryCode = columnScanner.next().trim();

                int estimatedHoursToFinish = estimatedHoursToFinishStr.isBlank() ? 0
                        : Integer.parseInt(estimatedHoursToFinishStr);
                boolean visible = visibleStr.equals("PÚBLICA");
                SubCategory subCategory = getSubCategoryByCode(subCategoryList, subCategoryCode);

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

                courses.add(course);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid courses.csv, please check!!!");
        }

        return courses;
    }

    public static SubCategory getSubCategoryByCode(List<SubCategory> subCategories, String subCategoryCode) {
        return subCategories.stream()
                .filter(x -> x.getCode().equals(subCategoryCode))
                .findAny()
                .orElse(null);
    }

}

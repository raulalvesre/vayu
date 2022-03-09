package br.com.vayu.services;

import br.com.vayu.models.Category;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryService {

    public static List<Category> getCategoriesListFromCsv(String resourcesFolderPath) {
        List<Category> categories = new ArrayList<>();

        InputStream categoriesInputStream = getInputStreamFromResources(resourcesFolderPath); //
        if (categoriesInputStream == null)
            throw new IllegalArgumentException(resourcesFolderPath + ".csv does not exist!");

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
            throw new IllegalArgumentException("Invalid .csv, please check!!!");
        }

        return categories;
    }

    private static InputStream getInputStreamFromResources(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    public static Category getCategoryByCode(List<Category> categories, String categoryCode) {
        return categories.stream()
                .filter(x -> x.getCode().equals(categoryCode))
                .findAny()
                .orElse(null);
    }

    public static void printActiveCategories(List<Category> categories) {
        System.out.println("ACTIVE CATEGORIES:");

        categories.stream()
                .filter(Category::isActive)
                .forEachOrdered(System.out::println);
    }

}

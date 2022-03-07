package br.com.vayu.services;

import br.com.vayu.models.Category;
import br.com.vayu.models.Subcategory;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SubcategoryService {

    public static List<Subcategory> getSubcategoriesListFromCsv(String resourcesFolderPath, List<Category> categoryList) {
        List<Subcategory> subCategories = new ArrayList<>();

        InputStream subCategoriesInputStream = getInputStreamFromResources(resourcesFolderPath);
        if (subCategoriesInputStream == null)
            throw new IllegalArgumentException(resourcesFolderPath + ".csv does not exist!");

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
                Category category = CategoryService.getCategoryByCode(categoryList, categoryCode);

                Subcategory subCategory = new Subcategory(
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
            throw new IllegalArgumentException("Invalid .csv, please check!!!");
        }

        return subCategories;
    }

    private static InputStream getInputStreamFromResources(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    public static Subcategory getSubcategoryByCode(List<Subcategory> subCategories, String subCategoryCode) {
        return subCategories.stream()
                .filter(x -> x.getCode().equals(subCategoryCode))
                .findAny()
                .orElse(null);
    }

    public static void printSubCategoriesWithNoDescription(List<Subcategory> subCategories) {
        System.out.println("SUB CATEGORIES WITHOUT DESCRIPTION");

        subCategories.stream()
                .filter(x -> x.getDescription() == null || x.getDescription().isBlank())
                .forEachOrdered(System.out::println);
    }

    public static void printQuantityOfActiveSubCategoriesWithDescription(List<Subcategory> subCategories) {
        long qtdSubCategoriesWithDescription = subCategories.stream()
                .filter(x -> x.isActive() && x.getDescription() != null && !x.getDescription().isBlank())
                .count();

        System.out.println("QUANTITY OF SUBCATEGORIES WITH DESCRIPTION: " + qtdSubCategoriesWithDescription);
    }

}

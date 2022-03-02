package br.com.vayu.helpers;

import br.com.vayu.models.Category;

import java.util.List;

public class CategoryHelper {

    public static void printActiveCategories(List<Category> categories) {
        System.out.println("ACTIVE CATEGORIES:");

        categories.stream()
                .filter(Category::isActive)
                .forEachOrdered(System.out::println);
    }

}

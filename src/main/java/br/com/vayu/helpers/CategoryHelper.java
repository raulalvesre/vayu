package br.com.vayu.helpers;

import br.com.vayu.models.Category;

import java.util.Collection;

public class CategoryHelper {

    public static void printActiveCategories(Collection<Category> categories) {
        System.out.println("ACTIVE CATEGORIES:");

        categories.stream()
                .filter(Category::isActive)
                .forEachOrdered(System.out::println);
    }

}

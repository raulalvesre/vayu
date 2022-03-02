package br.com.vayu.helpers;

import br.com.vayu.models.Category;

import java.util.Collection;

public class CategoryCollectionHelper {

    public static void printActiveCategories(Collection<Category> categories) {
        System.out.println("ACTIVE CATEGORIES:");

        categories.stream()
                .filter(Category::isActive)
                .forEachOrdered(System.out::println);
    }

}

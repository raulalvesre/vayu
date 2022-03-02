package br.com.vayu.helpers;

import br.com.vayu.models.SubCategory;

import java.util.Collection;

public class SubCategoryHelper {

    public static void printSubCategoriesWithNoDescription(Collection<SubCategory> subCategories) {
        System.out.println("SUB CATEGORIES WITHOUT DESCRIPTION");

        subCategories.stream()
                .filter(x -> x.getDescription() == null || x.getDescription().isBlank())
                .forEachOrdered(System.out::println);
    }

    public static void printQuantityOfActiveSubCategoriesWithDescription(Collection<SubCategory> subCategories) {
        long qtdSubCategoriesWithDescription = subCategories.stream()
                .filter(x -> x.isActive() && x.getDescription() != null && !x.getDescription().isBlank())
                .count();

        System.out.println("QUANTITY OF SUBCATEGORIES WITH DESCRIPTION: " + qtdSubCategoriesWithDescription);
    }

}

package br.com.vayu.dto;

import br.com.vayu.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryApiDTO {

    private String name;
    private String code;
    private int order;
    private String colorCode;
    private String studyGuide;
    private int totalOfCourses;
    private List<SubcategoryApiDTO> subcategories;

    public CategoryApiDTO(Category category, List<SubcategoryApiDTO> subcategories) {
        this.name = category.getName();
        this.code = category.getCode();
        this.order = category.getOrder();
        this.colorCode = category.getColorCode();
        this.studyGuide = category.getStudyGuide();
        this.subcategories = subcategories;

        this.totalOfCourses = subcategories.stream()
                .map(SubcategoryApiDTO::courses)
                .mapToInt(List::size)
                .sum();
    }

}
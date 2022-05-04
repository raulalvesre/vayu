package br.com.vayu.dto;

import br.com.vayu.models.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubcategoryCategoryPublicPageDTO {

    private int id;
    private String name;
    private String code;
    private List<CourseCategoryPublicPageDTO> courses;

    public SubcategoryCategoryPublicPageDTO(Subcategory subcategory, List<CourseCategoryPublicPageDTO> courses) {
        this.id = subcategory.getId();
        this.name = subcategory.getName();
        this.code = subcategory.getCode();
        this.courses = courses;
    }

}

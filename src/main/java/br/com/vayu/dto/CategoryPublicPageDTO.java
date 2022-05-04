package br.com.vayu.dto;

import br.com.vayu.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryPublicPageDTO {

    private String name;
    private String iconPath;
    private List<SubcategoryCategoryPublicPageDTO> subcategories;

    public CategoryPublicPageDTO(Category category, List<SubcategoryCategoryPublicPageDTO> subcategories) {
        this.name = category.getName();
        this.iconPath = category.getIconPath();
        this.subcategories = subcategories;
    }

}

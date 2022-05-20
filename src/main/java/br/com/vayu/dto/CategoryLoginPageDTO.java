package br.com.vayu.dto;

import br.com.vayu.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor

@NoArgsConstructor
@Getter
public class CategoryLoginPageDTO {

    private String name;
    private String code;
    private String iconPath;
    private List<String> subcategoriesNames;

    public CategoryLoginPageDTO(Category category, List<String> subcategoriesNames) {
        this.name = category.getName();
        this.code = category.getCode();
        this.iconPath = category.getIconPath();
        this.subcategoriesNames = subcategoriesNames;
    }

}

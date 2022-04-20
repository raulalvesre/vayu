package br.com.vayu.dto;

import br.com.vayu.models.Category;

import java.util.List;

public class CategoryLoginPageDTO {

    private String name;
    private String code;
    private String iconPath;
    private List<String> subcategoriesNames;

    public CategoryLoginPageDTO(String name, String code, String iconPath, List<String> subcategoriesNames) {
        this.name = name;
        this.code = code;
        this.iconPath = iconPath;
        this.subcategoriesNames = subcategoriesNames;
    }

    public CategoryLoginPageDTO(Category category, List<String> subcategoriesNames) {
        this.name = category.getName();
        this.code = category.getCode();
        this.iconPath = category.getIconPath();
        this.subcategoriesNames = subcategoriesNames;
    }

    @Deprecated
    public CategoryLoginPageDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public List<String> getSubcategoriesNames() {
        return subcategoriesNames;
    }

    public void setSubcategoriesNames(List<String> subcategoriesNames) {
        this.subcategoriesNames = subcategoriesNames;
    }
}

package br.com.vayu.dto;

import br.com.vayu.models.Category;

import java.util.List;

public class CategoryPublicPageDTO {

    private String name;
    private String iconPath;
    private List<SubcategoryCategoryPublicPageDTO> subcategories;

    public CategoryPublicPageDTO(String name, String iconPath, List<SubcategoryCategoryPublicPageDTO> subcategories) {
        this.name = name;
        this.iconPath = iconPath;
        this.subcategories = subcategories;
    }

    public CategoryPublicPageDTO(Category category, List<SubcategoryCategoryPublicPageDTO> subcategories) {
        this.name = category.getName();
        this.iconPath = category.getIconPath();
        this.subcategories = subcategories;
    }
    
    @Deprecated
    public CategoryPublicPageDTO() {}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public List<SubcategoryCategoryPublicPageDTO> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryCategoryPublicPageDTO> subcategories) {
        this.subcategories = subcategories;
    }

}

package br.com.vayu.builders;

import br.com.vayu.models.Category;
import br.com.vayu.models.Subcategory;

public class SubcategoryBuilder {

    private String code;
    private String name;
    private String description;
    private String studyGuide;
    private boolean active;
    private int order;
    private Category category;


    public SubcategoryBuilder code(String code) {
        this.code = code;
        return this;
    }
    
    public SubcategoryBuilder name(String name) {
        this.name = name;
        return this;
    }
    
    public SubcategoryBuilder description(String description) {
        this.description = description;
        return this;
    }
    
    public SubcategoryBuilder studyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
        return this;
    }

    public SubcategoryBuilder active(boolean active) {
        this.active = active;
        return this;
    }

    public SubcategoryBuilder order(int order) {
        this.order = order;
        return this;
    }

    public SubcategoryBuilder category(Category category) {
        this.category = category;
        return this;
    }

    public Subcategory build() {
        return new Subcategory(code,
                name,
                description,
                studyGuide,
                active,
                order,
                category);
    }

}

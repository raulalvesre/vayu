package br.com.vayu.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SubcategoryFormDTO {

    private int id;

    @NotBlank(message = "{name.notblank}")
    private String name;

    @NotBlank(message = "{code.notblank}")
    @Pattern(regexp = "^[a-z0-9-]+$", message = "{code.invalid}")
    private String code;

    private String description;
    private String studyGuide;
    private boolean active;
    private int order;
    private String categoryCode;

    public SubcategoryFormDTO(int id,
                              String name,
                              String code,
                              String description,
                              String studyGuide,
                              boolean active,
                              int order,
                              String categoryId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.studyGuide = studyGuide;
        this.active = active;
        this.order = order;
        this.categoryCode = categoryId;
    }

    @Deprecated
    public SubcategoryFormDTO() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public void setStudyGuide(String studyGuide) {
        this.studyGuide = studyGuide;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

}


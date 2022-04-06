package br.com.vayu.dto;

import br.com.vayu.models.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public final class CategoryFormDTO {

    private int id;

    @NotNull(message = "{name.notnull}")
    @NotBlank(message = "{name.notblank}")
    private String name;

    @NotNull(message = "{code.notnull")
    @NotBlank(message = "{code.notblank}")
    @Pattern(regexp = "^[a-z0-9-]+$", message = "{code.invalid}")
    private String code;

    private String description;
    private String studyGuide;
    private boolean active;
    private int order;
    private String iconPath;

    @Pattern(regexp = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$", message = "{colorcode.invalid}")
    private String colorCode;

    public CategoryFormDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.description = category.getDescription();
        this.studyGuide = category.getStudyGuide();
        this.active = category.isActive();
        this.order = category.getOrder();
        this.iconPath = category.getIconPath();
        this.colorCode = category.getColorCode();
    }

    @Deprecated
    public CategoryFormDTO() {}

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

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

}

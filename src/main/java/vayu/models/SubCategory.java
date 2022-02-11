package vayu.models;

import vayu.utils.TextValidator;

public class SubCategory {

    private String code;
    private String name;
    private String description;
    private String studyGuide;
    private boolean isActive;
    private int order;
    private Category category;

    public SubCategory(String code,
                       String name,
                       Category category) {
        setCode(code);
        setName(name);
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should only contain lower case letters, numbers and '-'");

        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Name should not be null or blank");

        this.name = name;
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
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}

package vayu.models;

import vayu.utils.TextValidator;

public class SubCategory {

    private final String code;
    private final String name;
    private final String description;
    private final String studyGuide;
    private final boolean isActive;
    private final int order;
    private final Category category;

    public SubCategory(String code,
                       String name,
                       String description,
                       String studyGuide,
                       boolean isActive,
                       int order,
                       Category category) {
        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should only contain lower case letters, numbers and '-'");

        if (name.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s name should not be null or empty");

        this.code = code;
        this.name = name;
        this.description = description;
        this.studyGuide = studyGuide;
        this.isActive = isActive;
        this.order = order;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStudyGuide() {
        return studyGuide;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getOrder() {
        return order;
    }

    public Category getCategory() {
        return category;
    }
}

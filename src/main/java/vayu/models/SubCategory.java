package vayu.models;

import vayu.utils.TextValidator;

public class SubCategory {

    private final String code;
    private final String name;
    private String description;
    private String studyGuide;
    private boolean isActive;
    private int order;
    private final Category category;

    public SubCategory(String code,
                       String name,
                       String description,
                       String studyGuide,
                       boolean isActive,
                       int order,
                       Category category) {
        this(code, name, category);

        this.description = description;
        this.studyGuide = studyGuide;
        this.isActive = isActive;
        this.order = order;
    }

    public SubCategory(String code,
                       String name,
                       Category category) {
        if (code == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s code should not be null");

        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s code should only contain lower case letters, numbers and '-'");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s name should not be null or blank");

        if (category == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s category should not be null");

        this.code = code;
        this.name = name;
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

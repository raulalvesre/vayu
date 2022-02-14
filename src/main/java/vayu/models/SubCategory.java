package vayu.models;

import static vayu.services.ValidationService.*;

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
        validateCode(code);
        validateName(name);
        validateCategory(category);

        this.code = code;
        this.name = name;
        this.category = category;
    }

    private void validateCode(String code) {
        validateIfItIsNull("code", code);
        validateIfIsBlankString("code", code);
        validateIfItIsValidCode(code);
    }

    private void validateName(String name) {
        validateIfItIsNull("name", name);
        validateIfIsBlankString("name", name);
    }

    private void validateCategory(Category category) {
        validateIfItIsNull("category", category);
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

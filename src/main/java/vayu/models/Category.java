package vayu.models;

import vayu.utils.TextValidator;

public class Category {

    private String code;
    private String name;
    private String description;
    private String studyGuide;
    private boolean isActive;
    private int order;
    private String imagePath;
    private String colorCode;

    public Category(String code,
                    String name) {
        setCode(code);
        setName(name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Category should only contain lower case letters, numbers and '-'");

        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Category should not be null or blank");

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        if (TextValidator.isHexColorCode(colorCode))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s color code should be a hex triplet");

        this.colorCode = colorCode;
    }
}

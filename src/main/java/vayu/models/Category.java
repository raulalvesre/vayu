package vayu.models;

import vayu.utils.TextValidator;

public class Category {

    private final String code;
    private final String name;
    private String description;
    private String studyGuide;
    private boolean isActive;
    private int order;
    private String imagePath;
    private String colorCode;

    public Category(String code,
                    String name,
                    String description,
                    String studyGuide,
                    boolean isActive,
                    int order,
                    String imagePath,
                    String colorCode) {
        this(code, name);

        this.description = description;
        this.studyGuide = studyGuide;
        this.isActive = isActive;
        this.order = order;
        this.imagePath = imagePath;
        this.colorCode = colorCode;
    }

    public Category(String code,
                    String name) {
        if (code == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s code should not be null");

        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s category should only contain lower case letters, numbers and '-'");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s category should not be null or blank");

        this.code = code;
        this.isActive = false;
        this.name = name;
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

    public String getImagePath() {
        return imagePath;
    }

    public String getColorCode() {
        return colorCode;
    }

}

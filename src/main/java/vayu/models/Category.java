package vayu.models;

import vayu.utils.TextValidator;

public class Category {

    private final String code;
    private final String name;
    private final String description;
    private final String studyGuide;
    private final boolean isActive;
    private final int order;
    private final String imagePath;
    private final String colorCode;

    public Category(String code,
                    String name,
                    String description,
                    String studyGuide,
                    boolean isActive,
                    int order,
                    String imagePath,
                    String colorCode) {
        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s Code should only contain lower case letters, numbers and '-'");

        this.code = code;
        this.name = name;
        this.description = description;
        this.studyGuide = studyGuide;
        this.isActive = isActive;
        this.order = order;
        this.imagePath = imagePath;
        this.colorCode = colorCode;
    }


}

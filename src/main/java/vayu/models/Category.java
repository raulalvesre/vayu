package vayu.models;

import static vayu.services.ValidationService.*;

public class Category {

    private final String code;
    private final String name;
    private String description;
    private String studyGuide;
    private boolean isActive;
    private int order;
    private String iconPath;
    private String colorCode;

    public Category(String code,
                    String name) {
        validateIfItIsValidCode(this.getClass().getSimpleName(), code);
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "name", name);

        this.code = code;
        this.name = name;
    }

}

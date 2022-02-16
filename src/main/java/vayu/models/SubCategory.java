package vayu.models;

import static vayu.services.ValidationService.*;

public class SubCategory {

    private final String code;
    private final String name;
    private String description;
    private String studyGuide;
    private boolean active;
    private int order;
    private final Category category;

    public SubCategory(String code,
                       String name,
                       Category category) {
        validateIfItIsValidCode(this.getClass().getSimpleName(), code);
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "name", name);
        validateIfItIsNull(this.getClass().getSimpleName(), "category", category);

        this.code = code;
        this.name = name;
        this.category = category;
    }

}

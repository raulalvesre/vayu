package vayu.models;

import vayu.enums.ValidationErrorType;
import vayu.services.TextValidationService;
import vayu.services.ValidationErrorMessageService;

public class Course {

    private final String code;
    private final String name;
    private final int estimatedHoursToFinish;
    private boolean isVisible;
    private String targetAudience;
    private final String instructorName;
    private String syllabus;
    private String developedAbilities;
    private final SubCategory subCategory;

    public Course(String code,
                  String name,
                  int estimatedHoursToFinish,
                  boolean isVisible,
                  String targetAudience,
                  String instructorName,
                  String syllabus,
                  String developedAbilities,
                  SubCategory subCategory) {
        this(code, name, estimatedHoursToFinish, instructorName, subCategory);

        this.isVisible = isVisible;
        this.targetAudience = targetAudience;
        this.syllabus = syllabus;
        this.developedAbilities = developedAbilities;
    }

    public Course(String code,
                  String name,
                  int estimatedHoursToFinish,
                  String instructorName,
                  SubCategory subCategory) {
        validateCode(code);
        validateName(name);
        validateEstimatedHoursToFinish(estimatedHoursToFinish);
        validateInstructorName(instructorName);
        validateSubCategory(subCategory);

        this.code = code;
        this.name = name;
        this.estimatedHoursToFinish = estimatedHoursToFinish;
        this.isVisible = false;
        this.instructorName = instructorName;
        this.subCategory = subCategory;
    }

    private void validateCode(String code) {
        if (code == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("code", ValidationErrorType.Null));

        if (!TextValidationService.isValidCode(code))
            throw new IllegalArgumentException(ValidationErrorMessageService.getModelCodeMessage());
    }

    private void validateName(String name) {
        if (name == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("name", ValidationErrorType.Null));

        if (name.isBlank())
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("name", ValidationErrorType.Blank));
    }

    private void validateEstimatedHoursToFinish(int estimatedHoursToFinish) {
        if (estimatedHoursToFinish < 1 || estimatedHoursToFinish > 20)
            throw  new IllegalArgumentException(ValidationErrorMessageService.getRangeMessage("estimated hours to finish", 1, 20));
    }

    private void validateInstructorName(String instructorName) {
        if (instructorName.isBlank())
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("instructor", ValidationErrorType.Blank));
    }

    private void validateSubCategory(SubCategory subCategory) {
        if (subCategory == null)
            throw new IllegalArgumentException(ValidationErrorMessageService.getMessage("sub category", ValidationErrorType.Null));
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getEstimatedHoursToFinish() {
        return estimatedHoursToFinish;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public String getDevelopedAbilities() {
        return developedAbilities;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

}

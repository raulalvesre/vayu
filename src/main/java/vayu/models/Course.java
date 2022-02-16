package vayu.models;

import static vayu.services.ValidationService.*;

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
        validateIfItIsNull(this.getClass().getSimpleName(), "code", code);
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "code", code);
        validateIfItIsValidCode(this.getClass().getSimpleName(), code);
    }

    private void validateName(String name) {
        validateIfItIsNull(this.getClass().getSimpleName(), "name", name);
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "name", name);
    }

    private void validateEstimatedHoursToFinish(int estimatedHoursToFinish) {
        validateIfIntIsWithinRange(this.getClass().getSimpleName(), "estimated hours to finish", estimatedHoursToFinish, 1, 20);
    }

    private void validateInstructorName(String instructorName) {
        validateIfItIsNull(this.getClass().getSimpleName(), "instructor name", instructorName);
        validateIfIsNullOrBlankString(this.getClass().getSimpleName(), "instructor name", instructorName);
    }

    private void validateSubCategory(SubCategory subCategory) {
        validateIfItIsNull(this.getClass().getSimpleName(), "sub category", subCategory);
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

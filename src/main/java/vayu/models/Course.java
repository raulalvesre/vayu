package vayu.models;

import vayu.utils.TextValidator;

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
        if (code == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s code should not be null");

        if (!TextValidator.containsOnlyNumbersAndLowerCaseLetters(code))
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s code should only contain lower case letters, numbers and '-'");

        if (name == null || name.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s name should not be null or blank");

        if (estimatedHoursToFinish < 1 || estimatedHoursToFinish > 20)
            throw  new IllegalArgumentException(this.getClass().getSimpleName() + "'s time to finish should be between 1 and 20");

        if (instructorName.isBlank())
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s instructor's name should not be blank");

        if (subCategory == null)
            throw new IllegalArgumentException(this.getClass().getSimpleName() + "'s sub category should not be null");

        this.code = code;
        this.name = name;
        this.estimatedHoursToFinish = estimatedHoursToFinish;
        this.isVisible = false;
        this.instructorName = instructorName;
        this.subCategory = subCategory;
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

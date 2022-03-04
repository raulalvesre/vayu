package br.com.vayu.models;

import br.com.vayu.services.ValidationService;

public class Course {

    private final String code;
    private final String name;
    private final int estimatedHoursToFinish;
    private boolean visible;
    private String targetAudience;
    private final String instructorName;
    private String syllabus;
    private String developedAbilities;
    private final Subcategory subCategory;

    public Course(String code,
                  String name,
                  int estimatedHoursToFinish,
                  boolean visible,
                  String targetAudience,
                  String instructorName,
                  String syllabus,
                  String developedAbilities,
                  Subcategory subCategory) {
        this(code, name, estimatedHoursToFinish, instructorName, subCategory);

        this.visible = visible;
        this.targetAudience = targetAudience;
        this.syllabus = syllabus;
        this.developedAbilities = developedAbilities;
    }

    public Course(String code,
                  String name,
                  int estimatedHoursToFinish,
                  String instructorName,
                  Subcategory subCategory) {
        ValidationService.validateIfItIsValidCode(code);
        ValidationService.validateIfIsBlankString("name", name);
        ValidationService.validateIfIntIsWithinRange("estimated hours to finish", estimatedHoursToFinish, 1, 20);
        ValidationService.validateIfIsBlankString("instructor name", instructorName);
        ValidationService.validateIfItIsNull("sub category", subCategory);

        this.code = code;
        this.name = name;
        this.estimatedHoursToFinish = estimatedHoursToFinish;
        this.instructorName = instructorName;
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public int getEstimatedHoursToFinish() {
        return estimatedHoursToFinish;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public Subcategory getSubCategory() {
        return subCategory;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", estimatedHoursToFinish=" + estimatedHoursToFinish +
                ", visible=" + visible +
                ", targetAudience='" + targetAudience + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", syllabus='" + syllabus + '\'' +
                ", developedAbilities='" + developedAbilities + '\'' +
                ", subCategory=" + subCategory.getName() +
                '}';
    }

}

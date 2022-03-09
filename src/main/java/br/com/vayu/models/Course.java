package br.com.vayu.models;

import java.util.Objects;

import static br.com.vayu.services.ValidationService.*;

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
        validateIfItIsValidCode(code);
        validateIfIsBlankString("name", name);
        validateIfIntIsWithinRange("estimated hours to finish", estimatedHoursToFinish, 1, 20);
        validateIfIsBlankString("instructor name", instructorName);
        validateIfItIsNull("sub category", subCategory);

        this.code = code;
        this.name = name;
        this.estimatedHoursToFinish = estimatedHoursToFinish;
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
        return visible;
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

    public Subcategory getSubCategory() {
        return subCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return estimatedHoursToFinish == course.estimatedHoursToFinish &&
                visible == course.visible && code.equals(course.code) &&
                name.equals(course.name) &&
                Objects.equals(targetAudience, course.targetAudience) &&
                instructorName.equals(course.instructorName) &&
                Objects.equals(syllabus, course.syllabus) &&
                Objects.equals(developedAbilities, course.developedAbilities) &&
                subCategory.equals(course.subCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name, estimatedHoursToFinish, visible, targetAudience, instructorName, syllabus, developedAbilities, subCategory);
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

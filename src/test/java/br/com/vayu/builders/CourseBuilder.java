package br.com.vayu.builders;

import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;

public class CourseBuilder {

    private String code;
    private String name;
    private int estimatedHoursToFinish;
    private boolean visible;
    private String targetAudience;
    private  String instructorName;
    private String syllabus;
    private String developedAbilities;
    private Subcategory subCategory;

    public CourseBuilder code(String code) {
        this.code = code;
        return this;
    }

    public CourseBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder estimatedHoursToFinish(int estimatedHoursToFinish) {
        this.estimatedHoursToFinish = estimatedHoursToFinish;
        return this;
    }

    public CourseBuilder visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public CourseBuilder targetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
        return this;
    }

    public CourseBuilder instructorName(String instructorName) {
        this.instructorName = instructorName;
        return this;
    }

    public CourseBuilder syllabus(String syllabus) {
        this.syllabus = syllabus;
        return this;
    }

    public CourseBuilder developedAbilities(String developedAbilities) {
        this.developedAbilities = developedAbilities;
        return this;
    }

    public CourseBuilder subcategory(Subcategory subcategory) {
        this.subCategory = subcategory;
        return this;
    }

    public Course build() {
        return new Course(code,
                name,
                estimatedHoursToFinish,
                visible,
                targetAudience,
                instructorName,
                syllabus,
                developedAbilities,
                subCategory);
    }

}

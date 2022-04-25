package br.com.vayu.dto;

import br.com.vayu.models.Course;

public class CourseCategoryPublicPageDTO {

    private String name;
    private int estimatedHoursToFinish;

    public CourseCategoryPublicPageDTO(String name, int estimatedHoursToFinish) {
        this.name = name;
        this.estimatedHoursToFinish = estimatedHoursToFinish;
    }

    public CourseCategoryPublicPageDTO(Course course) {
        this.name = course.getName();
        this.estimatedHoursToFinish = course.getEstimatedHoursToFinish();
    }

    @Deprecated
    public CourseCategoryPublicPageDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstimatedHoursToFinish() {
        return estimatedHoursToFinish;
    }

    public void setEstimatedHoursToFinish(int estimatedHoursToFinish) {
        this.estimatedHoursToFinish = estimatedHoursToFinish;
    }

}

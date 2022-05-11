package br.com.vayu.dto;

import br.com.vayu.models.Course;

public record CourseApiDTO(String name,
                           String code,
                           int estimatedHoursToFinish,
                           String developedAbilities) {

    public CourseApiDTO(Course course) {
        this(course.getName(),
                course.getCode(),
                course.getEstimatedHoursToFinish(),
                course.getDevelopedAbilities());
    }

}

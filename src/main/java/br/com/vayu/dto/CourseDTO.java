package br.com.vayu.dto;

import br.com.vayu.models.Course;

public record CourseDTO(String name,
                        String code,
                        int estimatedHoursToFinish,
                        String developedAbilities) {

    public CourseDTO(Course course) {
        this(course.getName(),
                course.getName(),
                course.getEstimatedHoursToFinish(),
                course.getDevelopedAbilities());
    }

}

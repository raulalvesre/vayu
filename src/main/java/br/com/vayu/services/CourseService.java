package br.com.vayu.services;

import br.com.vayu.projections.CourseMinifiedProjection;
import br.com.vayu.projections.DashboardInstructorProjection;
import br.com.vayu.repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Page<CourseMinifiedProjection> getPage(String subcategoryCode, Pageable pageable) {
        return courseRepository.findPageMinifiedBySubcategoryCode(subcategoryCode, pageable);
    }

    public DashboardInstructorProjection getDashboardInstructorProjection() {
        return courseRepository.getDashboardInstructorView();
    }

}

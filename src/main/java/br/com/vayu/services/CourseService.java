package br.com.vayu.services;

import br.com.vayu.projections.CourseMinifiedProjection;
import br.com.vayu.projections.DashboardInstructorProjection;
import br.com.vayu.repositories.CourseRepository;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Page<CourseMinifiedProjection> getPage(String subcategoryCode, Pageable pageable) {
        List<CourseMinifiedProjection> courses = courseRepository.findAllMinifiedBySubcategoryCode(subcategoryCode);

        PagedListHolder<CourseMinifiedProjection> holder = new PagedListHolder<>(courses);
        holder.setPage(pageable.getPageNumber());
        holder.setPageSize(pageable.getPageSize());

        return new PageImpl<>(holder.getPageList(), pageable, courses.size());
    }

    public DashboardInstructorProjection getDashboardInstructorProjection() {
        return courseRepository.getDashboardInstructorView();
    }

}

package br.com.vayu.services;

import br.com.vayu.dto.CourseFormDTO;
import br.com.vayu.exceptions.NotFoundException;
import br.com.vayu.models.Course;
import br.com.vayu.models.Subcategory;
import br.com.vayu.projections.CourseMinifiedProjection;
import br.com.vayu.projections.DashboardInstructorProjection;
import br.com.vayu.repositories.CourseRepository;
import br.com.vayu.repositories.SubcategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final SubcategoryRepository subcategoryRepository;

    public CourseService(CourseRepository courseRepository, SubcategoryRepository subcategoryRepository) {
        this.courseRepository = courseRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    public CourseFormDTO getByCodeAsFormDto(String courseCode) {
        return courseRepository.findByCodeAsFormDto(courseCode)
                .orElseThrow(() -> new NotFoundException("Course not found!"));
    }

    public Page<CourseMinifiedProjection> getPage(String subcategoryCode, Pageable pageable) {
        return courseRepository.findPageMinifiedBySubcategoryCode(subcategoryCode, pageable);
    }

    public DashboardInstructorProjection getDashboardInstructorProjection() {
        return courseRepository.getDashboardInstructorView();
    }

    @Transactional
    public Course save(CourseFormDTO courseFormDTO) {
        Subcategory subcategory = subcategoryRepository.findByCode(courseFormDTO.getSubcategoryCode())
                .orElseThrow(() -> new NotFoundException("Subcategory not found!"));

        Course model = new Course(courseFormDTO, subcategory);
        return courseRepository.save(model);
    }

    @Transactional
    public Course update(CourseFormDTO courseFormDTO) {
        Course bdCourse = courseRepository.findById(courseFormDTO.getId())
                .orElseThrow(() -> new NotFoundException("Course not found!"));

        Subcategory bdSubcategory = subcategoryRepository.findByCode(courseFormDTO.getSubcategoryCode())
                .orElseThrow(() -> new NotFoundException("Subcategory not found!"));

        bdCourse.merge(courseFormDTO, bdSubcategory);

        return courseRepository.save(bdCourse);
    }

}

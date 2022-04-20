package br.com.vayu.repositories;

import br.com.vayu.dto.CourseFormDTO;
import br.com.vayu.models.Course;
import br.com.vayu.projections.CourseMinifiedProjection;
import br.com.vayu.projections.DashboardInstructorProjection;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("""
            SELECT new br.com.vayu.dto.CourseFormDTO(crs.id, crs.name, crs.code, crs.estimatedHoursToFinish, crs.visible, crs.targetAudience, crs.instructorName, crs.syllabus, crs.developedAbilities, crs.subcategory.code)
            FROM Course crs
            WHERE crs.code = :crsCode""")
    Optional<CourseFormDTO> findByCodeAsFormDto(@Param("crsCode") String code);

    @Query("""
            SELECT crs
            FROM Course crs
                 INNER JOIN FETCH crs.subcategory sb
                 INNER JOIN FETCH sb.category ct
            WHERE ct.active = true AND sb.active = true AND crs.visible = true""")
    List<Course> findAllForCategoryApi();

    @Query(value = """
            SELECT instructor_name AS name, COUNT(course.id) AS totalCourses
            FROM course
            GROUP BY instructor_name
            ORDER BY totalCourses DESC
            LIMIT 1;""",
            nativeQuery = true)
    DashboardInstructorProjection getDashboardInstructorView();

    @Query(value = """
            SELECT crs.name, crs.code, crs.visible
            FROM course crs
                INNER JOIN subcategory sb ON crs.subcategory_id = sb.id
            WHERE sb.code = :sbCode""",
            nativeQuery = true)
    List<CourseMinifiedProjection> findAllMinifiedBySubcategoryCode(@Param("sbCode") String subcategoryCode);


    @Query("""
            SELECT crs
            FROM Course crs
                 INNER JOIN FETCH crs.subcategory sb
                 INNER JOIN FETCH sb.category ct
            WHERE ct.code = :ctCode AND sb.active = true AND crs.visible = true""")
    List<Course> findAllForCategoryPublicPage(@Param("ctCode") String categoryCode);


    default Page<CourseMinifiedProjection> findPageMinifiedBySubcategoryCode(String subcategoryCode, Pageable pageable) {
        List<CourseMinifiedProjection> courses = findAllMinifiedBySubcategoryCode(subcategoryCode);

        PagedListHolder<CourseMinifiedProjection> holder = new PagedListHolder<>(courses);
        holder.setPage(pageable.getPageNumber());
        holder.setPageSize(pageable.getPageSize());

        return new PageImpl<>(holder.getPageList(), pageable, courses.size());
    }

}

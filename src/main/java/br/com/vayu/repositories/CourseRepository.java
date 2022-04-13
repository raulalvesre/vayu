package br.com.vayu.repositories;

import br.com.vayu.models.Course;
import br.com.vayu.projections.CourseMinifiedProjection;
import br.com.vayu.projections.DashboardInstructorProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

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
            countQuery = """
                        SELECT count(*)
                        FROM course crs
                            INNER JOIN subcategory sb ON crs.subcategory_id = sb.id
                        WHERE sb.code = :sbCode""",
            nativeQuery = true)
    Page<CourseMinifiedProjection> findAllMinifiedBySubcategoryCode(@Param("sbCode") String subcategoryCode,
                                                                    Pageable pageable);

    @Query(value = """
                    SELECT crs.name, crs.code, crs.visible
                    FROM course crs
                        INNER JOIN subcategory sb ON crs.subcategory_id = sb.id
                    WHERE sb.code = :sbCode""",
            nativeQuery = true)
    List<CourseMinifiedProjection> findAllMinifiedBySubcategoryCode(@Param("sbCode") String subcategoryCode);

}

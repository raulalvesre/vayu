package br.com.vayu.repositories;

import br.com.vayu.dto.CategoryFormDTO;
import br.com.vayu.models.Category;
import br.com.vayu.projections.CategoryMinifiedProjection;
import br.com.vayu.projections.DashboardCategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByCode(String code);

    @Query(value = """
                    SELECT ct.id, ct.name, ct.code, ct.active
                    FROM category ct
                    ORDER BY ct.order""",
            nativeQuery = true)
    List<CategoryMinifiedProjection> findAllMinifiedInOrder();

    List<Category> findAllByOrderByOrder();

    @Query(value = """
            SELECT ct.id, ct.name, ct.code, ct.active
            FROM category ct
            WHERE ct.code = :ctCode""",
            nativeQuery = true)
    Optional<CategoryMinifiedProjection> findByCodeMinified(@Param("ctCode") String code);

    @Query("""
            SELECT new br.com.vayu.dto.CategoryFormDTO(ct.id, ct.name, ct.code, ct.description, ct.studyGuide, ct.active, ct.order, ct.iconPath, ct.colorCode)
            FROM Category ct
            WHERE ct.code = :ctCode""")
    Optional<CategoryFormDTO> findByCodeAsFormDto(@Param("ctCode") String code);

    @Query(value = """
            SELECT ct.name, COALESCE(count(c.id), 0) AS totalCourses
            FROM category ct
                     LEFT JOIN subcategory s on ct.id = s.category_id
                     LEFT JOIN course c on s.id = c.subcategory_id
            GROUP BY ct.id
            ORDER BY totalCourses DESC""",
            nativeQuery = true)
    List<DashboardCategoryProjection> findAllOrderedDescAsDashboardCategoryView();

    @Modifying
    @Query("""
            UPDATE Category ct
            SET ct.active = false
            WHERE ct.id = :ctId""")
    void deactivate(@Param("ctId") int id);


////
////    @Query(value = """
////                    SELECT *
////                    FROM category ct
////                             INNER JOIN subcategory sb ON sb.category_id = ct.id
////                    WHERE ct.code = :ctCode""",
////            nativeQuery = true)
//    List<CategoryApiProjection> findAllByCode(String ctCode);

}

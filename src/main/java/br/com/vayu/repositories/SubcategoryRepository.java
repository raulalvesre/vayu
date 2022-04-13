package br.com.vayu.repositories;

import br.com.vayu.dto.SubcategoryFormDTO;
import br.com.vayu.models.Subcategory;
import br.com.vayu.projections.SubcategoryMinifiedProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {

    @Query(value = """
            SELECT new br.com.vayu.dto.SubcategoryFormDTO(sb.id, sb.name, sb.code, sb.description, sb.studyGuide, sb.active, sb.order, sb.category.code)
            FROM Subcategory sb
            WHERE sb.code = :sbCode AND sb.category.code = :ctCode""")
    Optional<SubcategoryFormDTO> findByCodeAndCategoryCodeAsFormDto(@Param("sbCode") String sbCode, @Param("ctCode") String ctCode);

    @Query(value = """
            SELECT sb.id, sb.name, sb.code, sb.active
            FROM subcategory sb
                INNER JOIN category ct ON sb.category_id = ct.id
            WHERE sb.code = :sbCode AND ct.code = :ctCode""",
            nativeQuery = true)
    Optional<SubcategoryMinifiedProjection> findByCodeAndCategoryCodeMinified(@Param("sbCode") String sbCode,
                                                                      @Param("ctCode") String ctCode);

    @Query(value = """
            SELECT sb.id, sb.name, sb.code, sb.active
            FROM subcategory sb
                INNER JOIN category ct ON sb.category_id = ct.id
            WHERE ct.code = :ctCode""",
            nativeQuery = true)
    List<SubcategoryMinifiedProjection> findAllMinifiedByCategoryCode(@Param("ctCode") String ctCode);

    @Modifying
    @Query("""
            UPDATE Subcategory sb
            SET sb.active = false
            WHERE sb.id = :sbId""")
    void deactivate(@Param("sbId") int id);

}

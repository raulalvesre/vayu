package br.com.vayu.repositories;

import br.com.vayu.models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {

    List<Subcategory> findAllByCategoryId(int categoryId);

}

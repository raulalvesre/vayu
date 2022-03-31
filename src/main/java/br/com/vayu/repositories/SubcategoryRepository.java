package br.com.vayu.repositories;

import br.com.vayu.models.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {

    List<Subcategory> findAllByCategoryId(int categoryId);

}

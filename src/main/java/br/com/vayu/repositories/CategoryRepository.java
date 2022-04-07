package br.com.vayu.repositories;

import br.com.vayu.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByOrderByOrder();
    List<Category> findAllByActiveTrue();

    Category findByCode(String code);

}

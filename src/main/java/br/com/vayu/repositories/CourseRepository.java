package br.com.vayu.repositories;

import br.com.vayu.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findAllBySubcategoryId(int subcategoryId);

}

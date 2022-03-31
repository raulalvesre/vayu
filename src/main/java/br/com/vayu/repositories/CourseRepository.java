package br.com.vayu.repositories;

import br.com.vayu.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findAllBySubcategoryId(int subcategoryId);

}

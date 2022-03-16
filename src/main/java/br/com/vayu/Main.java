package br.com.vayu;

import br.com.vayu.dao.CourseDAO;
import br.com.vayu.dto.CreateCourseDTO;
import br.com.vayu.services.HtmlCreatorService;

public class Main {

    public static void main(String[] args) {
        HtmlCreatorService.generateCoursesHtml();

        CreateCourseDTO createCourseDTO = new CreateCourseDTO("code1",
                "name1",
                1,
                true,
                "audience",
                "Raul",
                "syllabus",
                "devAbilities",
                1);

        int id = CourseDAO.createCourse(createCourseDTO);
        System.out.println("o id add foi " + id);

        int affectedRows = CourseDAO.makeAllCoursesPublic();
        System.out.println(affectedRows);

        CourseDAO.deleteCourse("code1");
    }

}

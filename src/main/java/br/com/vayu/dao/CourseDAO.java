package br.com.vayu.dao;

import br.com.vayu.dto.CourseHtmlDTO;
import br.com.vayu.dto.CreateCourseDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static br.com.vayu.datasources.C3P0DataSource.getConnection;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class CourseDAO {

    public static List<CourseHtmlDTO> findAllByVisibleTrueAsCourseHtmlDTO() {
        String query = """
                SELECT c.id, c.name, c.estimated_hours_to_finish, sb.id, sb.name
                FROM course c
                INNER JOIN subcategory sb on sb.id = c.subcategory_id
                WHERE visible""";

        try (Connection con = getConnection();
             PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.execute();
            ResultSet rst = pstm.getResultSet();
            return toCourseDTOList(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<CourseHtmlDTO> toCourseDTOList(ResultSet rst) throws SQLException {
        List<CourseHtmlDTO> courses = new ArrayList<>();

        while (rst.next()) {
            int id = rst.getInt(1);
            String name = rst.getString(2);
            int estimatedHoursToFinish = rst.getInt(3);
            int subcategoryId = rst.getInt(4);
            String subcategoryName = rst.getString(5);

            CourseHtmlDTO course = new CourseHtmlDTO(id,
                    name,
                    estimatedHoursToFinish,
                    subcategoryId,
                    subcategoryName);

            courses.add(course);
        }

        return courses;
    }

    public static int createCourse(CreateCourseDTO courseDto) {
        String query = """
                INSERT INTO course
                VALUES (DEFAULT, ?, ?, ?, ?, ?, ? ,? ,?, ?)""";

        try (Connection con = getConnection();
             PreparedStatement pstm = con.prepareStatement(query, RETURN_GENERATED_KEYS)) {

            pstm.setInt(1, courseDto.subcategoryId());
            pstm.setString(2, courseDto.code());
            pstm.setString(3, courseDto.name());
            pstm.setInt(4, courseDto.estimatedHoursToFinish());
            pstm.setBoolean(5, courseDto.visible());
            pstm.setString(6, courseDto.targetAudience());
            pstm.setString(7, courseDto.instructorName());
            pstm.setString(8, courseDto.syllabus());
            pstm.setString(9, courseDto.developedAbilities());

            pstm.execute();

            ResultSet rst = pstm.getGeneratedKeys();
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int makeAllCoursesPublic() {
        String query = """
                UPDATE course
                SET visible = true
                WHERE visible = false""";

        try (Connection con = getConnection();
             PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.execute();
            return pstm.getUpdateCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteCourse(String courseCode) {
        String query = """
                DELETE FROM course
                WHERE code LIKE ?""";

        try (Connection con = getConnection();
             PreparedStatement pstm = con.prepareStatement(query)) {
            pstm.setString(1, "%" + courseCode + "%");
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

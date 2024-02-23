package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
       String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)";

       try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

           String firstname = teacher.getFirstname();
           String lastname = teacher.getLastname();

           if (firstname.isEmpty() || lastname.isEmpty()) {
               return null;
           }

           ps.setString(1, firstname);
           ps.setString(2, lastname);

           int n = ps.executeUpdate();

           if (n != 1) {
               return null;
           }

           return teacher;

//           JOptionPane.showMessageDialog(null, n + " rows affected", "Insert",
//                   JOptionPane.INFORMATION_MESSAGE);

       } catch (SQLException e) {
//           e.printStackTrace();
           throw new TeacherDAOException("SQL Insert Error in Teacher " + teacher);
       }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = teacher.getId();
            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            if (firstname.isEmpty() || lastname.isEmpty()) {
                return null;
            }

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            int n = ps.executeUpdate();

            if (n != 1) {
                return null;
            }

//            JOptionPane.showMessageDialog(null, n + " rows affected", "Update",
//                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOException("SQL Update Error in Teacher " + teacher);
        }
        return teacher;
    }

    @Override
    public void delete(Integer id) throws TeacherDAOException {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOException("SQL Delete Error in teacher with id: " + id);
        }
    }

    @Override
    public Teacher getById(Integer id) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE ID = ?";
        Teacher teacher = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME")
                );
            }
            return teacher;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOException("SQL Error in teacher with id: " + id);
        }
    }

    @Override
    public List<Teacher> getByLastname(String lastname) throws TeacherDAOException {
        String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
        ResultSet rs;
        List<Teacher> teachers = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, lastname + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME")
                );
                teachers.add(teacher);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOException("SQL Error in teacher with lastname: " + lastname);
        }
        return teachers;
    }
}

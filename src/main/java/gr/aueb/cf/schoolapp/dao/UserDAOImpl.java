package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.security.SecUtil;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    @Override
    public User insert(User user) throws UserDAOException {
        String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES (?, ?)";
        String username = "";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            username = user.getUsername();
            String password = user.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                return null;
            }

            ps.setString(1, username);
            ps.setString(2, password);

            int n = ps.executeUpdate();

            if (n != 1) {
                return null;
            }

            return user;

        } catch (SQLException e) {
//           e.printStackTrace();
            throw new UserDAOException("SQL Insert Error in User with username " + username);
        }
    }

    @Override
    public User update(User user) throws UserDAOException {
        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            int id = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                return null;
            }

//            String hashPass = SecUtil.hashPassword(password);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, id);

            int n = ps.executeUpdate();

            if (n != 1) {
                return null;
            }

        } catch (SQLException e) {
//            e.printStackTrace();
            throw new UserDAOException("SQL Update Error in User " + user);
        }
        return user;
    }

    @Override
    public void deleteByUsername(String username) throws UserDAOException {
        String sql = "DELETE FROM USERS WHERE USERNAME = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new UserDAOException("SQL Delete Error in User with username: " + username);
        }
    }

    @Override
    public void deleteById(Integer id) throws UserDAOException {
        String sql = "DELETE FROM USERS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new UserDAOException("SQL Delete Error in User with id: " + id);
        }
    }

    @Override
    public List<User> getByUsername(String username) throws UserDAOException {
        String sql = "SELECT * FROM USERS WHERE USERNAME LIKE ?";
        ResultSet rs;
        List<User> users = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD")
                );
                users.add(user);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new UserDAOException("SQL Error in User with username: " + username);
        }
        return users;
    }

    @Override
    public User getById(Integer id) throws UserDAOException {
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        User user = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD")
                );
            }
            return user;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new UserDAOException("SQL Error in user with id: " + id);
        }
    }
}

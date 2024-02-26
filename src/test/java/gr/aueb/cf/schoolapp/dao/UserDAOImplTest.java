package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {
    private static IUserDAO userDAO;

    @BeforeAll
    public static void setupClass() throws SQLException {
        userDAO = new UserDAOImpl();
        DBHelper.eraseData();
    }

    @BeforeEach
    public void setup() throws UserDAOException {
        createDummyData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    public void persistAndGetUser() throws UserDAOException {
        User user = new User();
        user.setUsername("annaUser");
        user.setPassword("annaPass");

        userDAO.insert(user);

        List<User> users = userDAO.getByUsername("annaUser");

        assertEquals(1, users.size());
    }

    @Test
    void update() throws UserDAOException {
        User user = new User();
        user.setId(2);
        user.setUsername("annaUser2");
        user.setPassword("annaPass2");

        userDAO.update(user);

        List<User> users = userDAO.getByUsername(user.getUsername());

        assertEquals(1, users.size());
    }

    @Test
    void deleteById() throws UserDAOException {
        int id = 1;
        userDAO.deleteById(id);

        User user = userDAO.getById(id);
        assertNull(user);
    }

    @Test
    void deleteByUsername() throws UserDAOException {
        String username = "petrosUser";
        userDAO.deleteByUsername(username);

        List<User> users = userDAO.getByUsername(username);
        assertEquals(users.size(), 0);
    }

    @Test
    void getByLastname() throws TeacherDAOException, UserDAOException {
        List<User> users = userDAO.getByUsername("panosUser");
        assertEquals(1, users.size());
    }

    @Test
    void getById() throws UserDAOException {
        int id = 1;
        User user = userDAO.getById(id);
        assertNotNull(user);
    }

    public static void createDummyData() throws UserDAOException {
        User user = new User();
        user.setUsername("panosUser");
        user.setPassword("panosPass");
        userDAO.insert(user);

        user.setUsername("petrosUser");
        user.setPassword("petrosPass");
        userDAO.insert(user);
    }

}
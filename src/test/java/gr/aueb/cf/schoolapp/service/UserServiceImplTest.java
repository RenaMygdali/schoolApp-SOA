package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp.dto.UserReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.UserUpdateDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private static IUserDAO userDAO = new UserDAOImpl();
    private static IUserService userService;

    @BeforeAll
    public static void setUpClass() throws SQLException {
        userService = new UserServiceImpl(userDAO);
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

    public void insertAndGetUser() throws UserDAOException, UserNotFoundException {
        UserInsertDTO userInsertDTO = new UserInsertDTO("dimitrisUser", "dimitrisPass");

        userService.insertUser(userInsertDTO);
        List<User> users = userService.getUserByUsername(userInsertDTO.getUsername());
        assertEquals(1, users.size());
    }

    public void updateUser() throws UserNotFoundException, UserDAOException {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(1, "mariaUser2", "mariaPass2");

        userService.updateUser(userUpdateDTO);
        List<User> users = userService.getUserByUsername(userUpdateDTO.getUsername());
        assertEquals(1, users.size());
    }

    public void deleteUserById() throws UserNotFoundException, UserDAOException {
        int id = 2;

        userService.deleteUserById(id);

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(id);
        });
    }

    public void deleteByUsername() throws UserNotFoundException, UserDAOException {
        String username = "dimitrisUser";

        userService.deleteUserByUsername(username);

        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserByUsername(username);
        });

    }

    public static void createDummyData() throws UserDAOException {
        User user = new User();
        user.setUsername("mariaUser");
        user.setPassword("mariaPass");
        userDAO.insert(user);

        user.setUsername("nikosUser");
        user.setPassword("nikosPass");
        userDAO.insert(user);
    }
}
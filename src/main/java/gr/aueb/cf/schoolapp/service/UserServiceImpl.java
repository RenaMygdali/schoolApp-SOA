package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp.dto.UserUpdateDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.security.SecUtil;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService {
    private final IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User insertUser(UserInsertDTO insertDTO) throws UserDAOException {
        if (insertDTO == null) return null;

        try {
            User user = map(insertDTO);
            return userDAO.insert(user);
        } catch (UserDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public User updateUser(UserUpdateDTO updateDTO) throws UserDAOException,
            UserNotFoundException {
        if (updateDTO == null) return null;
        User user;

        try {
            user = map(updateDTO);

            if (userDAO.getByUsername(user.getUsername()) == null) {
                throw new UserNotFoundException("User with username " + updateDTO.getUsername() + " was" +
                        " not found");
            }
            return userDAO.update(user);
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteUserByUsername(String username) throws UserNotFoundException, UserDAOException {
        if (username == null) return;

        try {

            if (userDAO.getByUsername(username) == null) {
                throw new UserNotFoundException("User with username " + username + " was not " +
                        "found");
            }
            userDAO.deleteByUsername(username);
        } catch (UserDAOException | UserNotFoundException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteUserById(Integer id) throws UserDAOException, UserNotFoundException {
        if (id == null) return;

        try {

            if (userDAO.getById(id) == null) {
                throw new UserNotFoundException("User with id " + id + " was not found");
            }
            userDAO.deleteById(id);

//            Teacher existingTeacher = Optional.ofNullable(teacherDAO.getById(id))
//                    .orElseThrow(() -> new TeacherNotFoundException("Teacher with id: " + id + " " +
//                            "was not found"));
//            teacherDAO.delete(existingTeacher.getId());
        } catch (UserDAOException | UserNotFoundException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<User> getUserByUsername(String username) throws UserDAOException,
            UserNotFoundException {
        List<User> users = new ArrayList<>();
        if (username == null) return users;

        try {

            if (userDAO.getByUsername(username) == null) {
                throw new UserNotFoundException("User with username " + username + " not " +
                        "found");
            }

            users = userDAO.getByUsername(username);
            return users;
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public User getUserById(Integer id) throws UserDAOException, UserNotFoundException {
        User user;

        if (id == null) return null;

        try {
            if (userDAO.getById(id) == null) {
                throw new UserNotFoundException("User with id " + id + " was not found");
            }
            user = userDAO.getById(id);
            return user;
        } catch (UserDAOException | UserNotFoundException e) {
            throw e;
        }
    }

    private User map(UserInsertDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword());
    }

    private User map(UserUpdateDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword());
    }
}

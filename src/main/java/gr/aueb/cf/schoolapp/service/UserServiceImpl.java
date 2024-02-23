package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp.dto.UserUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
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

            String hashPass = SecUtil.hashPassword(updateDTO.getPassword());

            return userDAO.update(user);
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteUser(String username) throws UserNotFoundException, UserDAOException {
        if (username == null) return;

        try {

            if (userDAO.getByUsername(username) == null) {
                throw new UserNotFoundException("User with username " + username + " was not " +
                        "found");
            }
            userDAO.delete(username);
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

    private User map(UserInsertDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword());
    }

    private User map(UserUpdateDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword());
    }
}

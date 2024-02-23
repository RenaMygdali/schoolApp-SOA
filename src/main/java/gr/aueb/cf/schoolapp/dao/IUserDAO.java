package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.User;

import java.util.List;

public interface IUserDAO {

    User insert(User user) throws UserDAOException;
    User update(User user) throws UserDAOException;
    void delete(String username) throws UserDAOException;
    List<User> getByUsername(String username) throws UserDAOException;
}

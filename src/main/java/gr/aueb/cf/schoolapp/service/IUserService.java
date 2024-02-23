package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.*;
import gr.aueb.cf.schoolapp.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserService {

    /**
     * Inserts a {@link User} based on the data carried
     * by the {@link UserInsertDTO}.
     *
     * @param insertDTO
     *          DTO object that contains the data
     *          of the {@link User} to be inserted.
     * @return
     *          The inserted {@link User} instance.
     * @throws UserDAOException
     *          if any DAO exception occurs.
     */
    User insertUser(UserInsertDTO insertDTO) throws UserDAOException;

    /**
     * Updates a {@link User} based on the data carried by
     * the {@link UserUpdateDTO}.
     *
     * @param updateDTO
     *          DTO object that contains the data of the new
     *          {@link User}.
     * @return
     *          the update instance of the {@link User}.
     * @throws UserDAOException
     *          if any DAO exception occurs.
     * @throws UserNotFoundException
     *          if any User identified by their username was not found.
     */
    User updateUser(UserUpdateDTO updateDTO) throws UserDAOException,
            UserNotFoundException;

    /**
     * Deletes a {@link User} based on its username.
     *
     * @param username
     *      the username of the {@link User} to be deleted.
     * @throws UserDAOException
     *      if any DAO exception occurs.
     * @throws UserNotFoundException
     *      if any {@link User} identified by its username was not found.
     */
    void deleteUser(String username) throws UserDAOException, UserNotFoundException;


    /**
     * Searches and gets back to the caller  list of the
     * {@link User} objects identified by their username or
     * username's initial letters.
     *
     * @param username
     *      a String object that contains the username or the letters
     *      that the username starts with, of the {@link User} objects
     *      we are looking for.
     * @return
     *      a List that contains the results of the search, that it a
     *      list of {@link User} objects.
     * @throws UserDAOException
     *      if any DAO exception occurs.
     * @throws UserNotFoundException
     *      if any {@link User} with the specific username was not found.
     */
    List<User> getUserByUsername(String username) throws UserDAOException, UserNotFoundException;

}

package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {

    /**
     * Inserts a {@link TeacherDTO} based on the data carried
     * by the {@link TeacherDTO}.
     *
     * @param insertDTO
     *          DTO object that contains the data.
     * @return
     *          The inserted teacher instance.
     * @throws TeacherDAOException
     *          if any DAO exception occurs.
     */
    Teacher insertTeacher(TeacherInsertDTO insertDTO) throws TeacherDAOException;

    /**
     * Updates a {@link Teacher} based on the data carried by
     * the {@link TeacherDTO}.
     *
     * @param updateDTO
     *          DTO object that contains the data of the new
     *          {@link Teacher}.
     * @return
     *          the update instance of the {@link Teacher}.
     * @throws TeacherDAOException
     *          if any DAO exception occurs.
     * @throws TeacherNotFoundException
     *          if any Teacher identified by their id was not found.
     */
    Teacher updateTeacher(TeacherUpdateDTO updateDTO) throws TeacherDAOException,
            TeacherNotFoundException;

    /**
     * Deletes a {@link Teacher} based on the data carried
     * by the {@link TeacherDTO}.
     *
     * @param id
     *      the id of the teacher to be deleted.
     * @throws TeacherDAOException
     *      if any DAO exception occurs.
     * @throws TeacherNotFoundException
     *      if any teacher identified by its id was not found.
     */
    void deleteTeacher(Integer id) throws TeacherDAOException, TeacherNotFoundException;


    /**
     * Searches and gets back to the caller a {@link Teacher} object
     * identified by its id.
     *
     * @param id
     *      the id of the teacher we search for.
     * @return
     *      the {@link Teacher} instance with the given id.
     * @throws TeacherDAOException
     *      if any DAO exception occurs.
     * @throws TeacherNotFoundException
     *      if any {@link Teacher} with the specific id
     *      was not found.
     */
    Teacher getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException;


    /**
     * Searches and gets back to the caller a list of the
     * {@link Teacher} objects identified by their lastnames
     * or lastname's initial letters.
     *
     * @param lastname
     *      a String object that contains the surname or the letters
     *      that the surname starts with, of the {@link Teacher}
     *      objects we are looking for.
     * @return
     *      a List that contains the results of the search,
     *      that is a List of {@link Teacher} objects.
     * @throws TeacherDAOException
     *      if any DAO exception occurs.
     * @throws TeacherNotFoundException
     *      if any {@link Teacher} with the given lastname was not found.
     */
    List<Teacher> getTeacherByLastname(String lastname) throws TeacherDAOException, TeacherNotFoundException;
}

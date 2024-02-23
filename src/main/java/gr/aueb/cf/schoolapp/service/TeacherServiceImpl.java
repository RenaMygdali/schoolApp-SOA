package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements ITeacherService {
    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(TeacherInsertDTO insertDTO) throws TeacherDAOException {
        if (insertDTO == null) return null;

        try {
            Teacher teacher = map(insertDTO);
            return teacherDAO.insert(teacher);
        } catch (TeacherDAOException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher updateTeacher(TeacherUpdateDTO updateDTO) throws TeacherNotFoundException,
            TeacherDAOException {
        if (updateDTO == null) return null;

        try {

            if (teacherDAO.getById(updateDTO.getId()) == null) {
                throw new TeacherNotFoundException("Teacher with id " + updateDTO.getId() + " was not found");
            }
            Teacher teacher = map(updateDTO);
//
//            Teacher existingTeacher = Optional.ofNullable(teacherDAO.getById(teacher.getId()))
//                    .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));

            return teacherDAO.update(teacher);
//            return Optional.ofNullable(teacherDAO.update(map(updateDTO)))
//                    .orElseThrow(() -> new RuntimeException("runtime exception"));
        } catch (TeacherDAOException | TeacherNotFoundException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTeacher(Integer id) throws TeacherNotFoundException, TeacherDAOException {
        if (id == null) return;

        try {

            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " was not found");
            }
            teacherDAO.delete(id);

//            Teacher existingTeacher = Optional.ofNullable(teacherDAO.getById(id))
//                    .orElseThrow(() -> new TeacherNotFoundException("Teacher with id: " + id + " " +
//                            "was not found"));
//            teacherDAO.delete(existingTeacher.getId());
        } catch (TeacherDAOException | TeacherNotFoundException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher getTeacherById(Integer id) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher;
        if (id == null) return null;

        try {

            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " not found");
            }
            teacher = teacherDAO.getById(id);

//            return Optional.ofNullable(teacherDAO.getById(id))
//                    .orElseThrow(() -> new TeacherNotFoundException("Teacher with id: " + id + " " +
//                            "was not found"));
            return teacher;
        } catch (TeacherDAOException | TeacherNotFoundException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Teacher> getTeacherByLastname(String lastname) throws TeacherDAOException, TeacherNotFoundException {
        List<Teacher> teachers = new ArrayList<>();
        if (lastname == null) return teachers;

        try {

            if (teacherDAO.getByLastname(lastname) == null) {
                throw new TeacherNotFoundException("Teacher with lastname " + lastname + " not " +
                        "found");
            }

            teachers = teacherDAO.getByLastname(lastname);

//            return teachers;
//            return Optional.ofNullable(teacherDAO.getByLastname(lastname))
//                    .orElseThrow(() -> new RuntimeException("Teacher with lastname: " + lastname + " " +
//                            "was not found"));
            return teachers;
        } catch (TeacherDAOException | TeacherNotFoundException e) {
//            e.printStackTrace();
            throw e;
        }
    }

    private Teacher map(TeacherInsertDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }

    private Teacher map(TeacherUpdateDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}

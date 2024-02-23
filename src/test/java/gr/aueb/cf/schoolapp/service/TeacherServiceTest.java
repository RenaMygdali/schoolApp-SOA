package gr.aueb.cf.schoolapp.service;

import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceTest {

    private static ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private static ITeacherService teacherService;

    @BeforeAll
    public static void setUpClass() throws SQLException {
        teacherService = new TeacherServiceImpl(teacherDAO);
        DBHelper.eraseData();
    }

    @BeforeEach
    public void setup() throws TeacherNotFoundException, TeacherDAOException {
         createDummyData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    public void insertAndGetTeacher() throws TeacherDAOException, TeacherNotFoundException {
        TeacherInsertDTO insertDTO = new TeacherInsertDTO("Anna", "Giannoutsou");

        teacherService.insertTeacher(insertDTO);
        List<Teacher> teachers = teacherService.getTeacherByLastname(insertDTO.getLastname());
        assertEquals(1, teachers.size());
    }

    @Test
    public void updateTeacher() throws TeacherNotFoundException, TeacherDAOException {
        TeacherUpdateDTO updateDTO = new TeacherUpdateDTO(2, "Athanassios2", "Androutsos2");
        teacherService.updateTeacher(updateDTO);

        List<Teacher> teachers = teacherService.getTeacherByLastname(updateDTO.getLastname());
        assertEquals(1, teachers.size());
    }

    @Test
    public void deleteTeacher() throws TeacherNotFoundException, TeacherDAOException {
        int id = 1;
        teacherService.deleteTeacher(1);

        Teacher teacher = teacherService.getTeacherById(id);
        assertNull(teacher);
    }

    @Test
    public void deleteInvalidTeacher() throws TeacherNotFoundException, TeacherDAOException {
        int id = 20;

        assertThrows(TeacherNotFoundException.class, () -> {
            teacherService.deleteTeacher(id);
        });
    }

    public static void createDummyData() throws TeacherNotFoundException, TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Anna");
        teacher.setLastname("Giannoutsou");
        teacherDAO.insert(teacher);

        teacher.setFirstname("Thanassis");
        teacher.setLastname("Androutsos");
        teacherDAO.insert(teacher);

        teacher.setFirstname("Alice");
        teacher.setLastname("Wonderland");
        teacherDAO.insert(teacher);

        teacher.setFirstname("John");
        teacher.setLastname("Lennon");
        teacherDAO.insert(teacher);
    }
}
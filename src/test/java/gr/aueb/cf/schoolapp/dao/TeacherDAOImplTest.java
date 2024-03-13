package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dao.util.DBHelper;
import gr.aueb.cf.schoolapp.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDAOImplTest {
    private static ITeacherDAO teacherDAO;

    /**
     * Εκτελείται μία φορά μόνο, μόλις φορτώνεται η κλάση.
     * Πρέπει να είναι static.
     *
     * @throws SQLException
     */
    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherDAO = new TeacherDAOImpl();
//        DBHelper.eraseData();
    }


    /**
     * Εκτελείται πριν από κάθε test.
     *
     * @throws TeacherDAOException
     */
    @BeforeEach
    public void setup() throws TeacherDAOException {
         createDummyData();
    }


    /**
     * Εκτελείται μετά από κάθε test.
     * Καθαρίζει τη ΒΔ.
     *
     * @throws SQLException
     */
    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }



    @Test
    public void persistAndGetTeacher() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Anna");
        teacher.setLastname("Kur.");

        teacherDAO.insert(teacher);

        List<Teacher> teachers = teacherDAO.getByLastname("Kur.");

        assertEquals(1, teachers.size());
    }

    @Test
    void update() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setId(2);
        teacher.setFirstname("AnnaUpdated");
        teacher.setLastname("GiannoutsouUpdated");

        teacherDAO.update(teacher);

        List<Teacher> teachers = teacherDAO.getByLastname(teacher.getLastname());

        assertEquals(1, teachers.size());
//        assertEquals(teachers.get(0).getFirstname(), "AnnaUpdated");
//        assertEquals(teachers.get(0).getLastname(), "GiannoutsouUpdated");
    }

    @Test
    void delete() throws TeacherDAOException {
        int id = 1;
        teacherDAO.delete(id);

        Teacher teacher = teacherDAO.getById(id);
        assertNull(teacher);
    }

    @Test
    void getByLastname() throws TeacherDAOException {
        List<Teacher> teachers = teacherDAO.getByLastname("Androu");
        assertEquals(1, teachers.size());
    }

    @Test
    void getById() throws TeacherDAOException {
        int id = 4;
        Teacher teacher = teacherDAO.getById(id);
        assertNotNull(teacher);
    }

    public static void createDummyData() throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Thanassis");
        teacher.setLastname("Androutsos");
        teacherDAO.insert(teacher);

        teacher.setFirstname("Anna");
        teacher.setLastname("Giannoutsou");
        teacherDAO.insert(teacher);

        teacher.setFirstname("Alice");
        teacher.setLastname("Wonderland");
        teacherDAO.insert(teacher);

        teacher.setFirstname("John");
        teacher.setLastname("Lennon");
        teacherDAO.insert(teacher);
    }
}
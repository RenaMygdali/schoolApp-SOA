package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void gettersSetters() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        assertEquals(teacher.getId(), 1);
        teacher.setFirstname("Bob");
        assertEquals(teacher.getFirstname(), "Bob");
        teacher.setLastname("Dylan");
        assertEquals(teacher.getLastname(), "Dylan");
    }

    @Test
    void overloadedConstructorAndToString() {
        Teacher teacher = new Teacher(2, "Kostas", "Pappas");
        assertEquals(teacher.getId(), 2);
        assertEquals(teacher.getFirstname(), "Kostas");
        assertEquals(teacher.getLastname(), "Pappas");
        String expected = "2, Kostas, Pappas";
        assertEquals(expected, teacher.toString());

    }

}
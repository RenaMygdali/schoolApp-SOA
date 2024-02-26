package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void gettersSetters() {
        User user = new User();

        user.setId(1);
        user.setUsername("renaUser");
        user.setPassword("renaPass");

        assertEquals(user.getId(), 1);
        assertEquals(user.getUsername(), "renaUser");
        assertEquals(user.getPassword(), "renaPass");
    }

    @Test
    void overloadedConstructorAndToString() {
        User user = new User(2, "sofiaUser", "sofiaPass");

        assertEquals(user.getId(), 2);
        assertEquals(user.getUsername(), "sofiaUser");
        assertEquals(user.getPassword(), "sofiaPass");

        String expected = "2, sofiaUser, sofiaPass";
        assertEquals(expected, user.toString());



    }

}
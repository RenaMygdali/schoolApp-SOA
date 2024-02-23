package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;

import java.util.HashMap;
import java.util.Map;

public class TeacherValidator {

    /**
     * No instances of this class should be available.
     */
    private TeacherValidator() {}


    // validate method for TeacherInsertDTO
    // Το 1ο String το όνομα του πεδίου στο οποίο συνέβη το λάθος
    // Το 2ο πεδίο, ένας ενδεικτικός κωδικός λάθους.
    public static Map<String, String> validate(TeacherInsertDTO teacherInsertDTO) {
        Map<String, String> errors = new HashMap<>();

        if (teacherInsertDTO.getFirstname().length() < 2 || teacherInsertDTO.getFirstname().length() > 32) {
            errors.put("firstname", "size");
        }

        if (teacherInsertDTO.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "whitespaces");
        }

        if (teacherInsertDTO.getLastname().length() < 2 || teacherInsertDTO.getLastname().length() > 32) {
            errors.put("lastname", "size");
        }

        if (teacherInsertDTO.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "whitespaces");
        }
        return errors;
    }


    // Overloaded validate method for TeacherUpdateDTO
    public static Map<String, String> validate(TeacherUpdateDTO teacherUpdateDTO) {
        Map<String, String> errors = new HashMap<>();

        if (teacherUpdateDTO.getFirstname().length() < 2 || teacherUpdateDTO.getFirstname().length() > 32) {
            errors.put("firstname", "size");
        }

        if (teacherUpdateDTO.getFirstname().matches("^.*\\s+.*$")) {
            errors.put("firstname", "whitespaces");
        }

        if (teacherUpdateDTO.getLastname().length() < 2 || teacherUpdateDTO.getLastname().length() > 32) {
            errors.put("lastname", "size");
        }

        if (teacherUpdateDTO.getLastname().matches("^.*\\s+.*$")) {
            errors.put("lastname", "whitespaces");
        }
        return errors;
    }
}

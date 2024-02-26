package gr.aueb.cf.schoolapp.validator;

import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.dto.UserInsertDTO;
import gr.aueb.cf.schoolapp.dto.UserUpdateDTO;

import java.util.HashMap;
import java.util.Map;

public class UserValidator {

    /**
     * No instances of this class should be available.
     */
    private UserValidator() {}


    // validate method for UserInsertDTO
    // Το 1ο String το όνομα του πεδίου στο οποίο συνέβη το λάθος
    // Το 2ο πεδίο, ένας ενδεικτικός κωδικός λάθους.
    public static Map<String, String> validate(UserInsertDTO userInsertDTO) {
        Map<String, String> errors = new HashMap<>();

        if (userInsertDTO.getUsername().length() < 2 || userInsertDTO.getUsername().length() > 32) {
            errors.put("username", "size");
        }

        if (userInsertDTO.getPassword().length() < 2 || userInsertDTO.getPassword().length() > 32) {
            errors.put("password", "size");
        }
        return errors;
    }


    // Overloaded validate method for TeacherUpdateDTO
    public static Map<String, String> validate(UserUpdateDTO userUpdateDTO) {
        Map<String, String> errors = new HashMap<>();

        if (userUpdateDTO.getUsername().length() < 2 || userUpdateDTO.getUsername().length() > 32) {
            errors.put("username", "size");
        }

        if (userUpdateDTO.getPassword().length() < 2 || userUpdateDTO.getPassword().length() > 32) {
            errors.put("password", "size");
        }
        return errors;
    }
}

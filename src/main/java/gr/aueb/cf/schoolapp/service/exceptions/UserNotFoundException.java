package gr.aueb.cf.schoolapp.service.exceptions;

import gr.aueb.cf.schoolapp.model.Teacher;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 7L;

    public UserNotFoundException(Teacher teacher) {
        super("Teacher with id: " + teacher.getId() + " does not exist");
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}

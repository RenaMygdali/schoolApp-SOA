package gr.aueb.cf.schoolapp.dto;

public class UserReadOnlyDTO extends BaseDTO {
    String firstname;
    String lastname;

    public UserReadOnlyDTO() {}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}

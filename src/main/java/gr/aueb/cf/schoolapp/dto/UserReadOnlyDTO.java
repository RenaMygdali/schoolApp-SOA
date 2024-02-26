package gr.aueb.cf.schoolapp.dto;

public class UserReadOnlyDTO extends BaseDTO {
    String username;
    String password;

    public UserReadOnlyDTO() {}

    public UserReadOnlyDTO(Integer id) {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

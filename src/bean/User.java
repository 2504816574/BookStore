package bean;

import java.io.Serializable;

/**
 *
 */
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer isadmin;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isadmin=" + isadmin +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    public User() {
    }

    public User(Integer id, String username, String password, String email, Integer isadmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isadmin = isadmin;
    }
}

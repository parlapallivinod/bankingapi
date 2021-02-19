package in.rgukt.r081247.bankingapi.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Valid
public class CustomHeader {
    @NotEmpty(message = "username must not be empty.")
    @Size(min = 5, max = 16, message = "username length must be between 5 and 16.")
    @Pattern(regexp="^([a-z])*$", message = "username should contain alphabets only")
    private String username;

    @NotEmpty(message = "password must not be empty.")
    @Size(min = 5, max = 16, message = "password length must be between 5 and 16.")
    private String password;

    public CustomHeader() {
    }
    public CustomHeader(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomHeader customHeader = (CustomHeader) o;
        return Objects.equals(username, customHeader.username) &&
                Objects.equals(password, customHeader.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "CustomHeader{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

/**
 * @author Vinod Parlapalli
 * Created on 2019/10/22
 * This class is used to represent the Users(Bankers, Customers) present
 * in this system.
 */

package in.rgukt.r081247.bankingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Comparable<User>{

    @Id
    @NotEmpty(message = "username must not be empty.")
    @Size(min = 5, max = 16, message = "username length must be between 5 and 16.")
    @Column(name = "username", length = 128, nullable = false)
    private String username;

    @NotEmpty(message = "password must not be empty.")
    @Size(min = 5, max = 16, message = "password length must be between 5 and 16.")
    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "balance", nullable = false)
    private Long balance = 0l;

    //@NotNull(message = "createdTime must not be empty")
    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "last_updated_time", nullable = true)
    private LocalDateTime lastUpdatedTime;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "rolename"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String password, Long balance, LocalDateTime createdTime, LocalDateTime lastUpdatedTime, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
        this.roles = roles;
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

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getBalance() {
        return this.balance;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + "'" +
                ", balance=" + balance +
                ", password='" + password + "'" +
                ", createdTime=" + createdTime +
                ", lastUpdatedTime=" + lastUpdatedTime +
                ", roles=" + roles +
                '}';
    }

    @Override
    public int compareTo(User user) {
        return this.getUsername().compareTo(user.getUsername());
    }
}

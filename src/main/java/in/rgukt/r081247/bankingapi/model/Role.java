/**
 * @author Vinod Parlapalli
 * Created on 2019/10/22
 * This class is used to represent the roles a user is assigned in the system.
 */

package in.rgukt.r081247.bankingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements Comparable<Role>{

    @Id
    @NotEmpty(message = "rolename must not be empty")
    @Size(max = 128)
    @Column(name = "rolename", length = 128, nullable = false)
    private String rolename;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(String rolename, Set<User> users) {
        this.rolename = rolename;
        this.users = users;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return getRolename().equals(role.getRolename());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRolename());
    }

    @Override
    public String toString() {
        return "Role{" +
                "rolename='" + rolename + '\'' +
                '}';
    }

    @Override
    public int compareTo(Role role) {
        return this.getRolename().compareTo(role.getRolename());
    }
}

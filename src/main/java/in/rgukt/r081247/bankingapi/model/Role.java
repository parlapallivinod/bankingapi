package in.rgukt.r081247.bankingapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @NotEmpty(message = "rolename must not be empty")
    @Size(max = 128)
    @Column(name = "rolename", length = 128, nullable = false)
    private String rolename;

    public Role() {
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
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
}

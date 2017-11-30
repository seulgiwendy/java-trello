package codesquad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    private static final String ROLE_PREFIX = "ROLE_";

    public enum RoleTypes {
        ROLE_ADMIN, ROLE_USER, ROLE_OWNER, ROLE_GUEST
    }

    public Role() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private RoleTypes role;

    public Role(RoleTypes role) {
        this.role = role;
    }

    public Role(String role) {
        this.role = RoleTypes.valueOf(ROLE_PREFIX + role);
    }

    public RoleTypes getRole() {
        return role;
    }
}
package codesquad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    public enum RoleTypes {
        ROLE_ADMIN, ROLE_OWNER, ROLE_GUEST
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private RoleTypes role;

    public Role(RoleTypes role) {
        this.role = role;
    }
}
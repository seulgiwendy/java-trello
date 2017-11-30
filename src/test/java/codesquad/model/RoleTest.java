package codesquad.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;

import static org.junit.Assert.*;

public class RoleTest {

    private Role role;

    @Before
    public void setUp() {
        this.role = new Role("ADMIN");
    }

    @Test
    public void 스트링생성자_잘먹는지() {
        assertEquals(Role.RoleTypes.ROLE_ADMIN, this.role.getRole());
    }

}
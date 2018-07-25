package codesquad.support;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RoleTest {

    @Test
    public void createRoleTest() {
        assertThat(Role.DEFAULT.getRoleName()).isEqualTo("DEFAULT");
        assertThat(Role.ADMIN.getRoleName()).isEqualTo("ADMIN");
    }
}
package codesquad.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    public void userCreate() {
        User user = new User();
        assertThat(user.isAdmin()).isFalse();
        AdminUser adminUser = new AdminUser();
        assertThat(adminUser.isAdmin()).isTrue();
    }
}
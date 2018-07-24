package codesquad.domain;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void validatePhone() {
        User user = new User();
        user.setPhone("abcd-010-1234");

    }
}
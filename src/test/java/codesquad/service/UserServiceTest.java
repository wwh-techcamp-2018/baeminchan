package codesquad.service;

import codesquad.domain.Authority;
import codesquad.domain.Role;
import codesquad.domain.User;
import codesquad.exception.UnAuthenticationException;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

public class UserServiceTest {
    @Resource
    private UserService userService;

    private Role normalRole;
    private Role adminRole;
    private User defaultUser;
    private User otherUser;

    @Before
    public void setUp() throws Exception {
        normalRole = new Role(Authority.NORMAL);
        adminRole = new Role(Authority.ADMIN);
        defaultUser = new User("gusdk@gusdk.com", "12341234", "권현아", "01040908370", normalRole);
        otherUser = new User("wngk@wngk.com", "12341234", "박주하", "01087562544", normalRole);
    }

    @Test
    public void login() throws UnAuthenticationException {
        assertThat(userService.login(defaultUser.getUserId(), defaultUser.getPassword())).isEqualTo(defaultUser);
    }
}
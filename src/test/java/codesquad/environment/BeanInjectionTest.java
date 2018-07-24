package codesquad.environment;

import codesquad.domain.User;
import codesquad.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanInjectionTest {

    @Autowired
    UserRepository userRepository;

    User user;

    @Before
    public void setUp(){
        user = new User("abcde@gmail.com", "password15", "가나다", "010-123-1234");
    }

    @Test
    public void repository_with_create(){
        userRepository.save(user);
        User dbUser = userRepository.findById(user.getId()).get();
        assertThat(dbUser.getName(), is(user.getName()));
    }
}

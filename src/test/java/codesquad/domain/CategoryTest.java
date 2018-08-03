package codesquad.domain;

import codesquad.exception.UnAuthorityException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

@Slf4j
public class CategoryTest {

    private User defaultUser;
    private User otherUser;
    private Category defaultCategory;

    @Before
    public void setUp() throws Exception {
        defaultUser = new User("javajigi@gmail.com", "testt@est134");
        otherUser = new User("serverwizard@naver.com", "testt@est134");
        defaultCategory = new Category(null, "밑반", defaultUser, LocalDateTime.now(), 1);
    }

    @Test(expected = UnAuthorityException.class)
    public void 타인이_생성_카테고리_삭제() {
        defaultCategory.deleteAll(otherUser);
    }
}

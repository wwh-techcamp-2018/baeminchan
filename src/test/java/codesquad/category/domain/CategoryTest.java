package codesquad.category.domain;

import codesquad.exception.UnAuthorizedException;
import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import codesquad.user.domain.User.UserBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    private UserBuilder userTemplate;
    private Category parentCategory;
    private Category category;

    @Before
    public void setUp() throws Exception {
        userTemplate = User.builder()
                .email("a@b.com")
                .name("name")
                .password("password")
                .phone("010-0000-0000");

        parentCategory = new Category();
        category = new Category();
    }

    @Test
    public void update() {
        User adminUser = userTemplate
                .role(Role.ADMIN)
                .build();

        category.update(adminUser, "name", parentCategory);

        assertThat(category.getName()).isEqualTo("name");
        assertThat(category.getParent()).isEqualTo(parentCategory);
    }

    @Test(expected = UnAuthorizedException.class)
    public void update_일반사용자() {
        User defaultUser = userTemplate
                .role(Role.DEFAULT)
                .build();

        category.update(defaultUser, "name", parentCategory);
    }
}

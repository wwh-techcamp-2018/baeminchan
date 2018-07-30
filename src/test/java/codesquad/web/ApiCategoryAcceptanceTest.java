package codesquad.web;

import codesquad.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import support.test.AcceptanceTest;
import support.test.RequestEntity;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiCategoryAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCategoryAcceptanceTest.class);
    private final String ADMIN_CATEGORIES = "/admin/categories";

    private Category category;
    private RequestEntity entityForCreate;

    @Before
    public void setup() {
        category = new Category();
        category.setTitle("맛반aaaa");
        entityForCreate = new RequestEntity.Builder()
                .withUrl(ADMIN_CATEGORIES)
                .withMethod(HttpMethod.POST)
                .withBody(category)
                .withReturnType(Void.class)
                .build();
    }

    @Test
    public void save() {
        assertThat(basicAuthRequest(entityForCreate, ADMIN_USER).getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    public void saveGuestUser() {
        assertThat(request(template(), entityForCreate).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void saveInvalidUser() {
        assertThat(basicAuthRequest(entityForCreate, DEFAULT_USER).getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
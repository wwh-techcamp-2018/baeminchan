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

    private RequestEntity.Builder entityBuilderForCreate;
    private RequestEntity.Builder entityBuilderForUpdate;

    @Before
    public void setup() {
        category = new Category();
        category.setTitle("original맛반");

        entityBuilderForCreate = new RequestEntity.Builder()
                .withUrl(ADMIN_CATEGORIES)
                .withMethod(HttpMethod.POST)
                .withBody(category)
                .withReturnType(Void.class);

        entityBuilderForUpdate = new RequestEntity.Builder()
                .withUrl(ADMIN_CATEGORIES + "/" + 1)
                .withMethod(HttpMethod.PUT)
                .withBody(category)
                .withReturnType(Void.class);
    }

    @Test
    public void save() {
        assertThat(basicAuthRequest(entityBuilderForCreate.build(), ADMIN_USER).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void saveParentNotExist() {
        assertThat(
                basicAuthRequest(entityBuilderForCreate.withUrl(ADMIN_CATEGORIES + "/0").build(), ADMIN_USER).getStatusCode()
        ).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void saveGuestUser() {
        assertThat(request(template(), entityBuilderForCreate.build()).getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void saveInvalidUser() {
        assertThat(basicAuthRequest(entityBuilderForCreate.build(), DEFAULT_USER).getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void update() {
        assertThat(basicAuthRequest(entityBuilderForUpdate.build(), ADMIN_USER).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void updateNotExistsCategory() {
        assertThat(
                basicAuthRequest(entityBuilderForUpdate.withUrl(ADMIN_CATEGORIES +"/0").build(), ADMIN_USER).getStatusCode()
        ).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
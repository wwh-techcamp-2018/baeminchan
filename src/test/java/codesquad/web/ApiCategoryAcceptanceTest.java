package codesquad.web;

import codesquad.domain.category.Category;
import codesquad.domain.category.CategoryRepository;
import codesquad.validation.RestResponse;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;
import support.test.RequestEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCategoryAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCategoryAcceptanceTest.class);
    private final String ADMIN_CATEGORIES = "/admin/categories";

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    private RequestEntity.Builder entityBuilderForCreate;
    private RequestEntity.Builder entityBuilderForUpdate;
    private RequestEntity.Builder entityBuilderForDelete;
    private RequestEntity.Builder entityBuilderForRead;

    @Before
    public void setup() {
        category = Category.valueOf(10L, "original맛반");

        entityBuilderForCreate = new RequestEntity.Builder()
                .withUrl(ADMIN_CATEGORIES)
                .withMethod(HttpMethod.POST)
                .withBody(category)
                .withReturnType(Category.class);

        entityBuilderForUpdate = new RequestEntity.Builder()
                .withUrl(ADMIN_CATEGORIES + "/4")
                .withMethod(HttpMethod.PUT)
                .withBody(category)
                .withReturnType(Category.class);

        entityBuilderForDelete = new RequestEntity.Builder()
                .withUrl(ADMIN_CATEGORIES + "/1")
                .withMethod(HttpMethod.DELETE)
                .withReturnType(Void.class);

        entityBuilderForRead = new RequestEntity.Builder()
                .withUrl("/categories")
                .withMethod(HttpMethod.GET)
                .withReturnType(RestResponse.class);
    }

    @Test
    public void save() {
        ResponseEntity<Category> responseEntity = basicAuthRequest(entityBuilderForCreate.build(), ADMIN_USER);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        categoryRepository.delete(responseEntity.getBody());
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
        assertThat(basicAuthRequest(entityBuilderForCreate.withReturnType(Void.class).build(), DEFAULT_USER).getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void update() {
        assertThat(basicAuthRequest(entityBuilderForUpdate.build(), ADMIN_USER).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void updateNotExistsCategory() {
        assertThat(
                basicAuthRequest(entityBuilderForUpdate.withUrl(ADMIN_CATEGORIES + "/0").build(), ADMIN_USER).getStatusCode()
        ).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void delete() {
        Category deleteCateogry = categoryRepository.save(category);
        assertThat(
                basicAuthRequest(entityBuilderForDelete.withUrl(ADMIN_CATEGORIES + "/" + deleteCateogry.getId()).build(), ADMIN_USER).getStatusCode()
        ).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteNotExistCategory() {
        assertThat(
                basicAuthRequest(entityBuilderForDelete.withUrl(ADMIN_CATEGORIES + "/0").build(), ADMIN_USER).getStatusCode()
        ).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getRootCategories() {
        ResponseEntity<RestResponse<List<Category>>> responseEntity = basicAuthRequest(entityBuilderForRead.build(), DEFAULT_USER);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getData().size()).isEqualTo(6);
        log.debug("data : {}", responseEntity.getBody().getData());
    }

    @Test
    public void category() {
        ResponseEntity<Category> responseEntity = basicAuthRequest(
                entityBuilderForRead.withUrl("/categories/5").withReturnType(Category.class).build(),
                DEFAULT_USER
        );
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Category category = (Category) responseEntity.getBody();
        assertThat(category.getChildren().size()).isEqualTo(0);
    }

}
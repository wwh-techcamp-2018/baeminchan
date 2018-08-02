package codesquad.category.web;

import codesquad.category.domain.Category;
import codesquad.category.domain.CategoryRepository;
import codesquad.category.dto.CategoryDto;
import codesquad.support.AcceptanceTest;
import codesquad.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiCategoryControllerTest extends AcceptanceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void getMainCategory() {
        ResponseEntity<RestResponse> response = template().getForEntity("/categories", RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).asList().isNotEmpty();
    }

    @Test
    public void getSubCategory() {
        ResponseEntity<RestResponse> response = template().getForEntity("/categories/1", RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).asList().isNotEmpty();
    }

    @Test
    public void createCategory() {
        CategoryDto newCategory = CategoryDto.builder()
                .name("야식")
                .build();

        ResponseEntity<Void> response = adminAuthTemplate().postForEntity("/admin/categories", newCategory, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        String location = response.getHeaders().getLocation().getPath();
        assertThat(location).isNotNull();
    }

    @Test
    public void createCategory_일반사용자실패() {
        CategoryDto newCategory = CategoryDto.builder()
                .name("야식")
                .build();

        ResponseEntity<Void> response = basicAuthTemplate().postForEntity("/admin/categories", newCategory, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void createCategory_로그인안함() {
        CategoryDto newCategory = CategoryDto.builder()
                .name("야식")
                .build();

        ResponseEntity<Void> response = template().postForEntity("/admin/categories", newCategory, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void updateCategory() {
        CategoryDto newCategory = CategoryDto.builder()
                .name("위반찬")
                .build();
        Long targetId = 1L;

        ResponseEntity<Category> response = adminAuthTemplate().exchange("/admin/categories/" + targetId, HttpMethod.PUT, createHttpEntityWithBody(newCategory), Category.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(newCategory.getName());
        assertThat(response.getBody().getId()).isEqualTo(targetId);
    }

    @Test
    public void updateCategory_일반사용자실패() {
        CategoryDto newCategory = CategoryDto.builder()
                .name("위반찬")
                .build();
        Long targetId = 1L;

        ResponseEntity<Category> response = basicAuthTemplate().exchange("/admin/categories/" + targetId, HttpMethod.PUT, createHttpEntityWithBody(newCategory), Category.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void updateCategory_로그인안함() {
        CategoryDto newCategory = CategoryDto.builder()
                .name("위반찬")
                .build();
        Long targetId = 1L;

        ResponseEntity<Category> response = template().exchange("/admin/categories/" + targetId, HttpMethod.PUT, createHttpEntityWithBody(newCategory), Category.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void deleteCategory() {
        Long targetId = 1L;

        ResponseEntity<Void> response = adminAuthTemplate().exchange("/admin/categories/" + targetId, HttpMethod.DELETE, createHttpEntity(), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteCategory_일반사용자() {
        Long targetId = 1L;

        ResponseEntity<Void> response = basicAuthTemplate().exchange("/admin/categories/" + targetId, HttpMethod.DELETE, createHttpEntity(), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
    @Test
    public void deleteCategory_로그인안함() {
        Long targetId = 1L;

        ResponseEntity<Void> response = template().exchange("/admin/categories/" + targetId, HttpMethod.DELETE, createHttpEntity(), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}

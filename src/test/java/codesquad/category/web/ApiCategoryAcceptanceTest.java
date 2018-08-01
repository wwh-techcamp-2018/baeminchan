package codesquad.category.web;

import codesquad.category.domain.Category;
import codesquad.category.domain.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiCategoryAcceptanceTest extends AcceptanceTest {

    @Test
    public void list() {
        ResponseEntity<Category> response = template.getForEntity("/category", Category.class);

        log.info("category : {}", response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void create() {
        CategoryDto dto = CategoryDto.builder()
                .name("test")
                .build();
        ResponseEntity<Void> response = basicAuthTemplate(defaultAdmin()).postForEntity("/admin/category", dto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo((HttpStatus.CREATED));
        assertThat(response.getHeaders().getLocation().getPath()).isNotEmpty();
    }

    @Test
    public void create_일반유저(){
        CategoryDto dto = CategoryDto.builder()
                .name("test")
                .build();
        ResponseEntity<Void> response = basicAuthTemplate(defaultUser()).postForEntity("/admin/category",dto,Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void update() {
        CategoryDto createDto = CategoryDto.builder()
                .name("test")
                .build();

        String location = createResource("/admin/category", createDto, defaultAdmin());

        CategoryDto updateDto = CategoryDto.builder()
                .parentId(2L)
                .name("update-test")
                .build();

        ResponseEntity<Void> response = basicAuthTemplate()
                .exchange("/admin" + location, HttpMethod.PUT, createHttpEntityWithBody(updateDto), Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void delete() {
        CategoryDto createDto = CategoryDto.builder()
                .name("test")
                .build();

        String location = createResource("/admin/category", createDto, defaultAdmin());

        ResponseEntity<Void> response = basicAuthTemplate()
                .exchange("/admin" + location, HttpMethod.DELETE, createHttpEntity(), Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}

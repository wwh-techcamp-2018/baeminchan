package codesquad.atdd;

import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Java6Assertions.assertThat;

@Slf4j
public class ApiAdminAcceptanceTest extends AcceptanceTest {

    @Test
    public void 실패_로그인안했을때() {
        CategoryDto category = new CategoryDto();
        RequestEntity<Object> request = RequestEntity.post(URI.create("/admin/api/categories")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(category);
        ResponseEntity<Void> response = template().exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void 실패_권한없을때() {
        CategoryDto category = new CategoryDto();
        RequestEntity<Object> request = RequestEntity.post(URI.create("/admin/api/categories")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(category);
        ResponseEntity<Void> response = basicAuthTemplate().exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).startsWith("/users/login");
    }

    @Test
    public void addCategory_성공(){
        CategoryDto categoryDto = new CategoryDto("NEW CATEGORY");
        RequestEntity<CategoryDto> requestEntity = RequestEntity.post(URI.create("/admin/api/categories")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(categoryDto);
        ResponseEntity<Category> response = basicAuthTemplate(adminUser()).exchange(requestEntity, Category.class);
        log.debug("response body : {}", response.getBody());
        Assertions.assertThat(response.getBody().getName()).contains(categoryDto.getName());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void addSubCategory_성공(){
        CategoryDto categoryDto = new CategoryDto("NEW SUB CATEGORY");
        RequestEntity<CategoryDto> requestEntity = RequestEntity.post(URI.create("/admin/api/categories/1")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(categoryDto);
        ResponseEntity<Category> response = basicAuthTemplate(adminUser()).exchange(requestEntity, Category.class);
        log.debug("response body : {}", response.getBody());
        Assertions.assertThat(response.getBody().getName()).contains(categoryDto.getName());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void delete_성공(){
        Category category = createCategory(1L);
        RequestEntity<Void> requestEntity = RequestEntity.delete(URI.create("/admin/api/categories/"+category.getId())).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<Category> response = basicAuthTemplate(adminUser()).exchange(requestEntity, Category.class);
        log.debug("response body : {}", response.getBody());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void delete_Root_카테고리_실패(){
        RequestEntity<Void> requestEntity = RequestEntity.delete(URI.create("/admin/api/categories/"+ 0L)).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<Category> response = basicAuthTemplate(adminUser()).exchange(requestEntity, Category.class);
        log.debug("response body : {}", response.getBody());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private Category createCategory(Long parentId){
        CategoryDto categoryDto = new CategoryDto("NEW SUB CATEGORY");
        RequestEntity<CategoryDto> requestEntity = RequestEntity.post(URI.create("/admin/api/categories/"+parentId)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(categoryDto);
        ResponseEntity<Category> response = basicAuthTemplate(adminUser()).exchange(requestEntity, Category.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        return response.getBody();
    }
}

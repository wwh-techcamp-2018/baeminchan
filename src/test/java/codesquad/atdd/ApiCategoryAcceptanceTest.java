package codesquad.atdd;

import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCategoryAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    @Test
    public void getAllCategoryJson() {
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create("/api/categories")).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<List<Category>> response = template().exchange(requestEntity, new ParameterizedTypeReference<List<Category>>() {
        });
        log.debug("response body : {}", response.getBody());
        assertThat(response.getBody()).hasSize(8);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void addCategory_성공(){
        CategoryDto categoryDto = new CategoryDto("NEW CATEGORY");
        RequestEntity<CategoryDto> requestEntity = RequestEntity.post(URI.create("/api/categories")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(categoryDto);
        ResponseEntity<Category> response = template().exchange(requestEntity, Category.class);
        log.debug("response body : {}", response.getBody());
        assertThat(response.getBody()).extracting("name").contains(categoryDto.getName());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void addSubCategory_성공(){
        CategoryDto categoryDto = new CategoryDto("NEW SUB CATEGORY");
        RequestEntity<CategoryDto> requestEntity = RequestEntity.post(URI.create("/api/categories/1")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(categoryDto);
        ResponseEntity<Category> response = template().exchange(requestEntity, Category.class);
        log.debug("response body : {}", response.getBody());
        assertThat(response.getBody()).extracting("name").contains(categoryDto.getName());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void delete_성공(){
        Category category = createCategory(1L);
        Category subCategory = createCategory(category.getId());
        RequestEntity<Void> requestEntity = RequestEntity.delete(URI.create("/api/categories/"+category.getId())).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<Category> response = template().exchange(requestEntity, Category.class);
        log.debug("response body : {}", response.getBody());
        Category deletedCategory = response.getBody();
        getAllCategoryJson();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private Category createCategory(Long parentId){
        CategoryDto categoryDto = new CategoryDto("NEW SUB CATEGORY");
        RequestEntity<CategoryDto> requestEntity = RequestEntity.post(URI.create("/api/categories/"+parentId)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(categoryDto);
        ResponseEntity<Category> response = template().exchange(requestEntity, Category.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        return response.getBody();
    }
}

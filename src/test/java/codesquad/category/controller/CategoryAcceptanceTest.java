package codesquad.category.controller;

import codesquad.category.domain.Category;
import codesquad.category.domain.CategoryRepository;
import codesquad.category.dto.CategoryDto;
import codesquad.support.dto.response.ResponseModel;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryAcceptanceTest extends AcceptanceTest {

    @Autowired
    private CategoryRepository categoryRepository;
    private ParameterizedTypeReference<ResponseModel<Category>> reference;
    private ParameterizedTypeReference<ResponseModel<List<Category>>> listReference;

    @Before
    public void setUp() throws Exception {
        reference = new ParameterizedTypeReference<ResponseModel<Category>>() {
        };
        listReference = new ParameterizedTypeReference<ResponseModel<List<Category>>>() {
        };
    }

    @Test
    public void createCategory() {
        CategoryDto categoryDto = new CategoryDto("밥", null);
        ResponseEntity<ResponseModel<Category>> response = basicAdminRequest("/api/category", HttpMethod.POST,
                categoryDto, reference);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        categoryRepository.delete(response.getBody().getData());
    }

    @Test
    public void createCategoryFail() {
        CategoryDto categoryDto = new CategoryDto(null, null);
        ResponseEntity<ResponseModel<Category>> response = basicAdminRequest("/api/category", HttpMethod.POST,
                categoryDto, reference);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createCategoryNotAdmin() {
        CategoryDto categoryDto = new CategoryDto("밥", null);
        ResponseEntity<ResponseModel<Category>> response = basicUserRequest("/api/category", HttpMethod.POST,
                categoryDto, reference);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void createCategoryWrongParentId() {
        CategoryDto categoryDto = new CategoryDto("밥", Long.MAX_VALUE);
        ResponseEntity<ResponseModel<Category>> response = basicAdminRequest("/api/category", HttpMethod.POST,
                categoryDto, reference);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void deleteCategory() {
        Category category = new Category("temp", null);
        Long saved = categoryRepository.save(category).getId();
        ResponseEntity<ResponseModel<Category>> response = basicAdminRequest("/api/category/" + saved, HttpMethod.DELETE,
                null, reference);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void deleteCategoryNotAdmin() {
        ResponseEntity<ResponseModel<Category>> response = basicUserRequest("/api/category/1", HttpMethod.DELETE,
                null, reference);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void deleteCategoryNotFound() {
        ResponseEntity<ResponseModel<Category>> response = basicAdminRequest("/api/category/" + Long.MAX_VALUE, HttpMethod.DELETE,
                null, reference);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void getTopCategory() {
        ResponseEntity<ResponseModel<List<Category>>> response = basicUserRequest("/api/category", HttpMethod.GET,
                null, listReference);

        assertThat(response.getBody().getData().size()).isEqualTo(8);
    }

    @Test
    public void getSubCategory() {
        ResponseEntity<ResponseModel<List<Category>>> response = basicUserRequest("/api/category/1", HttpMethod.GET,
                null, listReference);

        assertThat(response.getBody().getData().size()).isEqualTo(9);
    }
}

package codesquad.controller;

import codesquad.dto.TestCategoryDto;
import codesquad.dto.category.CategoryDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiAdminAcceptanceTest extends AcceptanceTest {
    private TestCategoryDto categoryDto;

    @Before
    public void setUp() throws Exception {
        categoryDto = new TestCategoryDto();
        categoryDto.setTitle("아이사랑111");
    }

    @Test
    public void createCategoryByAdminUser() {
        ResponseEntity responseEntity = basicAuthTemplate().postForEntity("/api/admin/category", categoryDto, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        categoryDto.setParentId(1L);
        responseEntity = basicAuthTemplate().postForEntity("/api/admin/category", categoryDto, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void createCategoryByNormalUser() {
        ResponseEntity responseEntity = basicAuthTemplate("user","qwer1234!!").postForEntity("/api/admin/category", categoryDto, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        categoryDto.setParentId(1L);
        responseEntity = basicAuthTemplate("user","qwer1234!!").postForEntity("/api/admin/category", categoryDto, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void deleteCategoryByAdminUser() {
        categoryDto.setCategoryId(50L);
        HttpEntity httpEntity = new HttpEntity(null, null);
        ResponseEntity responseEntity = basicAuthTemplate("admin","qwer1234!!").exchange("/api/admin/category/50", HttpMethod.DELETE, httpEntity, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteCategoryByNormalUser() {
        categoryDto.setCategoryId(50L);
        HttpEntity httpEntity = new HttpEntity(null, null);
        ResponseEntity responseEntity = basicAuthTemplate("user","qwer1234!!").exchange("/api/admin/category/50", HttpMethod.DELETE, httpEntity, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void updateCategoryByNormalUser() {
        categoryDto.setParentId(3L);
        categoryDto.setCategoryId(50L);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(categoryDto, httpHeaders);
        ResponseEntity responseEntity = basicAuthTemplate("user","qwer1234!!").exchange("/api/admin/category", HttpMethod.PUT, httpEntity, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }


    @Test
    public void updateCategoryByAdminUser() {
        categoryDto.setParentId(3L);
        categoryDto.setCategoryId(50L);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(categoryDto, httpHeaders);
        ResponseEntity responseEntity = basicAuthTemplate("admin","qwer1234!!").exchange("/api/admin/category", HttpMethod.PUT, httpEntity, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void updateNoParentCategoryByAdminUser() {
        categoryDto.setCategoryId(50L);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(categoryDto, httpHeaders);
        ResponseEntity responseEntity = basicAuthTemplate("admin","qwer1234!!").exchange("/api/admin/category", HttpMethod.PUT, httpEntity, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void updateInvalidParentCategoryByAdminUser() {
        categoryDto.setParentId(100L);
        categoryDto.setCategoryId(50L);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(categoryDto, httpHeaders);
        ResponseEntity responseEntity = basicAuthTemplate("admin","qwer1234!!").exchange("/api/admin/category", HttpMethod.PUT, httpEntity, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void updateInvalidCategoryByAdminUser() {
        categoryDto.setCategoryId(100L);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(categoryDto, httpHeaders);
        ResponseEntity responseEntity = basicAuthTemplate("admin","qwer1234!!").exchange("/api/admin/category", HttpMethod.PUT, httpEntity, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
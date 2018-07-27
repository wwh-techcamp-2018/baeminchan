package codesquad.web;

import codesquad.domain.Category;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;
import support.test.RequestEntity;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiCategoryAcceptanceTest extends AcceptanceTest {

    @Test
    public void save(){
        Category category = new Category();
        category.setTitle("맛반!");
        ResponseEntity responseEntity = basicAuthRequest(
                new RequestEntity.Builder()
                .withUrl("/admin/categories")
                .withMethod(HttpMethod.POST)
                .withBody(category)
                .withReturnType(Void.class)
                .build(),
                adminUser()
        );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
package codesquad.web;

import codesquad.dto.CategoryDto;
import codesquad.dto.CategoryListDto;
import codesquad.dto.CategoryPostDto;
import org.junit.Test;
import org.springframework.http.*;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiAdminAcceptanceTest extends AcceptanceTest {


    @Test
    public void add() {
        CategoryPostDto categoryPostDto = new CategoryPostDto("국·찌개", "대따맛있는국");
        ResponseEntity<Void> postResponse = basicAdminAuthTemplate().postForEntity("/api/admin/category", categoryPostDto, Void.class);
        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<CategoryListDto> getResponse = template().getForEntity("/api/category", CategoryListDto.class);
        assertThat(getResponse.getBody().getChildren().get(1).getName()).isEqualTo("국·찌개");
        assertThat(getResponse.getBody().getChildren().get(1).getChildren()).contains(new CategoryDto("대따맛있는국"));
    }

    @Test
    public void delete() {
        CategoryPostDto categoryPostDto = new CategoryPostDto("밑반찬", "나물무침");
        ResponseEntity<Void> deleteResponse =
                basicAdminAuthTemplate().exchange("/api/admin/category", HttpMethod.DELETE, createHttpEntity(categoryPostDto), Void.class);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<CategoryListDto> getResponse = template().getForEntity("/api/category", CategoryListDto.class);
        assertThat(getResponse.getBody().getChildren().get(0).getName()).isEqualTo("밑반찬");
        assertThat(getResponse.getBody().getChildren().get(0).getChildren().size()).isEqualTo(8);
    }


    private HttpEntity createHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(body, headers);
    }
}

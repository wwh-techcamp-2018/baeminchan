package codesquad.web;

import codesquad.dto.CategoryListDto;
import org.junit.Test;
import org.springframework.http.*;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCategoryAcceptanceTest extends AcceptanceTest {

    @Test
    public void show() {
        ResponseEntity<CategoryListDto> response = template().getForEntity("/api/category", CategoryListDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getFirstChild().getName()).isEqualTo("밑반찬");
        assertThat(response.getBody().getFirstChild().getFirstChild().getName()).isEqualTo("무침");
    }

}

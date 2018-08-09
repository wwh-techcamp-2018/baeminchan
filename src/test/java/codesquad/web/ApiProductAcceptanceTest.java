package codesquad.web;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiProductAcceptanceTest  extends AcceptanceTest {

    @Test
    public void search() {
        ResponseEntity<List<String>> response = requestForEntityWithParameterized("/api/products/search/연어",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("연어장", "자연어처리");
        assertThat(response.getBody()).doesNotContain("맛없는반찬1");
    }

    @Test
    public void searchNotFound() {
        ResponseEntity<List<String>> response = requestForEntityWithParameterized("/api/products/search/a",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

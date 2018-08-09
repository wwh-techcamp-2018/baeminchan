package codesquad.web;

import codesquad.domain.BestCategory;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class ApiBestCategoryAcceptanceTest extends AcceptanceTest {
    @Test
    public void showAll() {
        ResponseEntity<List<BestCategory>> response =
                requestForEntityWithParameterized("/api/best-categories",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<BestCategory>>(){});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

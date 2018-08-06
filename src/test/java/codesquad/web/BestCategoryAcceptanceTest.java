package codesquad.web;

import codesquad.domain.BestCategory;
import codesquad.support.AcceptanceTestTemplate;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BestCategoryAcceptanceTest extends AcceptanceTestTemplate {
    @Test
    public void show() {
        ResponseEntity<List<BestCategory>> response =
                getForEntityWithParameterized("/api/best-categories", new ParameterizedTypeReference<List<BestCategory>>(){});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isNotZero();
    }
}

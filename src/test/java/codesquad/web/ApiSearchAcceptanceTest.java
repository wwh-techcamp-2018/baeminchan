package codesquad.web;

import codesquad.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;
import support.test.RequestEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiSearchAcceptanceTest extends AcceptanceTest {

    private RequestEntity.Builder builder;

    @Before
    public void setUp() throws Exception {
        builder = new RequestEntity.Builder();
    }

    @Test
    public void search() {
        RequestEntity searchRequestEntity = builder.withUrl("/search/recommendations?keyword=소고기")
                .withMethod(HttpMethod.GET)
                .withReturnType(List.class)
                .build();

        ResponseEntity<List<Product>> responseEntity = request(template(), searchRequestEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(6);
    }
}

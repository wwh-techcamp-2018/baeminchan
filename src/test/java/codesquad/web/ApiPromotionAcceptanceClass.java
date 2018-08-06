package codesquad.web;

import codesquad.dto.PromotionDto;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import support.test.AcceptanceTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiPromotionAcceptanceClass extends AcceptanceTest {

    @Test
    public void show() {
        ResponseEntity<List<PromotionDto>> response =
                requestForEntityWithParameterized("/api/promotion", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<PromotionDto>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isNotZero();
    }
}

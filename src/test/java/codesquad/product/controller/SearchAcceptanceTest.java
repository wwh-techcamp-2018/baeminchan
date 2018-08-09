package codesquad.product.controller;

import codesquad.support.dto.response.ResponseModel;
import codesquad.support.test.AcceptanceTest;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchAcceptanceTest extends AcceptanceTest {

    @Test
    public void search() {
        String data = "된장";

        ResponseEntity<ResponseModel<List<String>>> response =
                basicUserRequest(uriBuild(data),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ResponseModel<List<String>>>() {
                        });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).containsExactly("된장국", "된장찌개", "해물된장찌개", "된장두부조림", "된장겉절이", "소고기된장찌개");
    }


    private String uriBuild(String data) {
        return UriComponentsBuilder.fromPath("/api/search/autocomplete").queryParam("q", data).build().toUriString();
    }
}

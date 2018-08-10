package codesquad.web;

import codesquad.dto.SearchRecommendationDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import support.test.AcceptanceTest;
import support.test.RequestEntity;


import static org.assertj.core.api.Assertions.assertThat;

public class ApiSearchAcceptanceTest extends AcceptanceTest {

    private RequestEntity.Builder builder;

    @Before
    public void setUp() throws Exception {
        builder = new RequestEntity.Builder();
    }

    @Test
    public void search() {
        UriComponents uriComponent = UriComponentsBuilder.fromUriString("/search/recommendations")
                .queryParam("keyword", "소고기").build();

        RequestEntity searchRequestEntity = builder.withUrl(uriComponent.toUriString())
                .withMethod(HttpMethod.GET)
                .withReturnType(SearchRecommendationDto.class)
                .build();

        ResponseEntity<SearchRecommendationDto> responseEntity = request(template(), searchRequestEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getKeyword()).isEqualTo("소고기");
    }
}

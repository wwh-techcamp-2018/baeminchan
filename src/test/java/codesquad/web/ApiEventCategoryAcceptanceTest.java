package codesquad.web;

import codesquad.domain.EventCategory;
import codesquad.dto.EventCategoryDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;
import support.test.RequestEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiEventCategoryAcceptanceTest extends AcceptanceTest {

    private RequestEntity.Builder builder;

    @Before
    public void setUp() throws Exception {
        builder = new RequestEntity.Builder();
    }

    @Test
    public void readEventCategories() {
        RequestEntity readRequestEntity = builder
                .withUrl("/eventcategories")
                .withMethod(HttpMethod.GET)
                .withReturnType(List.class)
                .build();

        ResponseEntity<List<EventCategory>> readResponseEntity = request(template(), readRequestEntity);
        assertThat(readResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(readResponseEntity.getBody().size()).isEqualTo(6);
    }

    @Test
    public void readEventCategory() {
        RequestEntity readRequestEntity = builder
                .withUrl("/eventcategories/1")
                .withMethod(HttpMethod.GET)
                .withReturnType(EventCategoryDto.class)
                .build();

        ResponseEntity<EventCategoryDto> readResponseEntity = request(template(), readRequestEntity);
        assertThat(readResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(readResponseEntity.getBody().getProducts().size()).isEqualTo(3);
    }

}
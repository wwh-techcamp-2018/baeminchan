package codesquad.category.web;

import codesquad.support.AcceptanceTest;
import codesquad.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiCategoryControllerTest extends AcceptanceTest {

    @Test
    public void getMainCategory() {
        ResponseEntity<RestResponse> response = template().getForEntity("/categories", RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).asList().isNotEmpty();
    }

    @Test
    public void getSubCategory() {
        ResponseEntity<RestResponse> response = template().getForEntity("/categories/1", RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).asList().isNotEmpty();
    }

}

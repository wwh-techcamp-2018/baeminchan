package codesquad.bestcategory;

import codesquad.support.AcceptanceTest;
import codesquad.utils.RestResponse;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiBestCategoryControllerTest extends AcceptanceTest {

    @Test
    public void getEventCategory() {
        ResponseEntity<RestResponse> response = template().getForEntity("/categories/event", RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).asList().isNotEmpty();
    }

    @Test
    public void getEventBanchan() {
        ResponseEntity<RestResponse> response = template().getForEntity("/categories/event/1/banchans", RestResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).asList().isNotEmpty();
    }
}

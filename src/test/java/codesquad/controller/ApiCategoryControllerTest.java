package codesquad.controller;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCategoryControllerTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCategoryController.class);

    @Test
    public void 카테고리_리스트() {
        ResponseEntity<Iterable> response = template().getForEntity("/api/categories", Iterable.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("message = {}", response.getBody());
    }
}



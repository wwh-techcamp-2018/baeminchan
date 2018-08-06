package codesquad.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiBestCategoryControllerTest extends AcceptanceTest {

    @Test
    public void 베스트_카테고리_리스트() {
        ResponseEntity<Iterable> response = template().getForEntity("/api/bestCategories", Iterable.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("message = {}", response.getBody());
    }

    @Test
    public void 반찬_리스트() {
        ResponseEntity<Iterable> response = template().getForEntity("/api/bestCategories/1", Iterable.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("message = {}", response.getBody());
    }

}
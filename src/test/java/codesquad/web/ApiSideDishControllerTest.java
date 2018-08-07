package codesquad.web;

import codesquad.domain.Category;
import codesquad.domain.SideDish;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

@Slf4j
public class ApiSideDishControllerTest extends AcceptanceTest {
    @Test
    public void 검색어_조회() {
        ResponseEntity<SideDish[]> response = template().getForEntity(String.format("/api/sidedishes?query=%s", "김"), SideDish[].class);
        log.debug("response.getBody.length : {}", response.getBody().length);
    }

}
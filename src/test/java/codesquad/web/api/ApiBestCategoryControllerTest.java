package codesquad.web.api;

import codesquad.domain.BestCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiBestCategoryControllerTest extends AcceptanceTest {

    @Test
    public void 베스트_카테고리_조회() {
        ResponseEntity<BestCategory[]> response = template().getForEntity("/api/bestcategories", BestCategory[].class);
        log.info("response.getBody() : {}", response.getBody());
        assertThat(response.getBody().length).isEqualTo(6);
    }
}

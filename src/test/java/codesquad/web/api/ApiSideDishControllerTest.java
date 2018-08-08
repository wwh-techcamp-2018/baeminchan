package codesquad.web.api;

import codesquad.domain.SideDish;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiSideDishControllerTest extends AcceptanceTest {
    @Test
    public void 검색어_조회() {
        ResponseEntity<SideDish[]> response = template().getForEntity(String.format("/api/side/dishes?query=%s", "김"), SideDish[].class);
        assertThat(response.getBody().length).isEqualTo(4);
        assertThat(response.getBody()).contains(new SideDish("김포카리", 1000, 800, "이것은 설명입니다", 100));
    }

}
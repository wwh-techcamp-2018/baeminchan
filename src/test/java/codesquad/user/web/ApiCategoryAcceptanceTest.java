package codesquad.user.web;

import codesquad.category.domain.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiCategoryAcceptanceTest extends AcceptanceTest {

    @Test
    public void list() {
        ResponseEntity<Category> response = template.getForEntity("/category", Category.class);

        log.info("category : {}", response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

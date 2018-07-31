package codesquad.atdd;

import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import codesquad.service.CategoryService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCategoryAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserAcceptanceTest.class);

    @Test
    public void getAllCategoryJson() {
        RequestEntity<Void> requestEntity = RequestEntity.get(URI.create("/api/categories")).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<List<Category>> response = template().exchange(requestEntity, new ParameterizedTypeReference<List<Category>>() {});
        log.debug("response body : {}", response.getBody());
        assertThat(response.getBody()).hasSize(8);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


}

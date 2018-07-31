package codesquad.controller;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import codesquad.exception.NotMatchedException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import support.test.AcceptanceTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCategoryControllerTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCategoryController.class);

    @Autowired
    private CategoryRepository categoryRepository;
    private User defaultUser;
    private User other;
    private CategoryDto categoryDto;

    @Before
    public void setUp() throws Exception {
        defaultUser = new User("javajigi@naver.com", "test33##");
        other = new User("gusdk@naver.com", "test33##");
        categoryDto = new CategoryDto("테스트 카테고리", 1L, 1);
    }

    @Test
    public void 카테고리_리스트() {
        ResponseEntity<Iterable> response = template().getForEntity("/api/categories", Iterable.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.info("message = {}", response.getBody());
    }

    @Test
    public void 카테고리_생성() {
        ResponseEntity<Void> response = basicAuthTemplate().postForEntity("/api/categories", categoryDto, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


    @Test
    public void 카테고리_수정_정상유저() {
        ResponseEntity<Void> response = basicAuthTemplate(defaultUser).exchange(String.format("/api/categories/%d", 1L), HttpMethod.PUT, new HttpEntity(categoryDto.setName("수정된 카테고리")), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getCategoryByName(categoryDto.getName())).isNotNull();
    }

    @Test
    public void 카테고리_수정_비정상유저() {
        ResponseEntity<Void> response = basicAuthTemplate(other).exchange(String.format("/api/categories/%d", 1L), HttpMethod.PUT, new HttpEntity(categoryDto), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void 카테고리_삭제_정상유저() {
        ResponseEntity<Void> response = basicAuthTemplate(defaultUser).exchange(String.format("/api/categories/%d", 1L), HttpMethod.DELETE, new HttpEntity(new HttpHeaders()), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 카테고리_삭제_비정상유저() {
        ResponseEntity<Void> response = basicAuthTemplate(other).exchange(String.format("/api/categories/%d", 1L), HttpMethod.DELETE, new HttpEntity(new HttpHeaders()), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    private Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(() -> new NotMatchedException("일치하는 카테고리가 없습니다."));
    }
}



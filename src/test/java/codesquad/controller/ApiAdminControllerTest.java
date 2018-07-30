package codesquad.controller;

import codesquad.domain.CategoryRepository;
import codesquad.dto.CategoryDto;
import codesquad.web.HtmlFormDataBuilder;
import controller.ApiUserControllerTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiAdminControllerTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiAdminControllerTest.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
//        Map<String, String> newCategory = new HashMap<>();
//        newCategory.put("parentId", "1");
//        newCategory.put("name", "테스트 카테고리");
//        newCategory.put("priority", "1");

        CategoryDto categoryDto = new CategoryDto("테스트 카테고리", 1L, 1);

        ResponseEntity<Void> response = basicAuthTemplate().postForEntity("/api/admin/categories", categoryDto, Void.class);
        log.info("category : {}", categoryRepository.findByName("테스트 카테고리").get());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
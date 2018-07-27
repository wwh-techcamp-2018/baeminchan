package codesquad.web;


import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.dto.CategoryDto;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

public class ApiCategoryControllerTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiCategoryControllerTest.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void 뎁스1_카테고리_등록_성공() {
        CategoryDto categoryDto = new CategoryDto("밑반찬");
        ResponseEntity<Category> response = template().postForEntity("/api/categories", categoryDto, Category.class);
        log.debug("response.getBody : {}", response.getBody());
    }

    @Test
    public void 뎁스2_카테고리_등록_성공() {
        Category category = categoryRepository.findById(1L).get();
        CategoryDto categoryDto2 = new CategoryDto("무침");
        ResponseEntity<Category> response2 = template().postForEntity("/api/categories/1", categoryDto2, Category.class);
        log.debug("response.getBody : {}", response2.getBody());
    }
}

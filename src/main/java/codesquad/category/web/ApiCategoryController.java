package codesquad.category.web;

import codesquad.category.domain.Category;
import codesquad.category.domain.CategoryRepository;
import codesquad.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RestController
public class ApiCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public ResponseEntity<RestResponse> list() {
        return listById(Category.ROOT);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<RestResponse> listById(@PathVariable long id) {
        Category sub = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        log.info("sub: {}", sub);
        List<Category> categoryList = sub.getChildren();
        log.info("category List: {}", categoryList);
        return ResponseEntity.ok().body(RestResponse.builder().data(categoryList).build());
    }

}

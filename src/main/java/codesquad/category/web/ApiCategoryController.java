package codesquad.category.web;

import codesquad.category.domain.Category;
import codesquad.category.domain.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/category")
@Slf4j
public class ApiCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<Category> list() {
        Category category = categoryRepository.findByParentIsNull()
                .orElseThrow(EntityNotFoundException::new);

        return ResponseEntity.ok(category);
    }
}

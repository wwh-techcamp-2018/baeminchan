package codesquad.web;

import codesquad.domain.Category;
import codesquad.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/categories")
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("")
    public ResponseEntity<Category> list() {
        return new ResponseEntity<>(categoryService.findRoot(), HttpStatus.OK);

    }
}

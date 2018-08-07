package codesquad.web;



import codesquad.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity show() {
        return ResponseEntity.ok().body(categoryService.getParents());
    }
}

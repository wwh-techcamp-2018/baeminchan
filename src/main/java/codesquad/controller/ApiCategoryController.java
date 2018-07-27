package codesquad.controller;

import codesquad.domain.Category;
import codesquad.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/categories")
public class ApiCategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("")
    public Iterable<Category> list() {
        return categoryService.getParentList();
    }

//    @GetMapping("")
//    public ResponseEntity<Iterable<Category>> list() {
//        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getParentList());
//    }

}

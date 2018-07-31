package codesquad.web;


import codesquad.domain.Category;
import codesquad.dto.CategoryListDto;
import codesquad.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/category")
public class ApiCategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping
    public CategoryListDto show() {
        return categoryService.getParents();
    }

//    @GetMapping
//    public ResponseEntity<CategoryListDto> show() {
//        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(categoryService.getParents());
//    }

}

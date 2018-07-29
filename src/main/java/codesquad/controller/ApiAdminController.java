package codesquad.controller;


import codesquad.domain.category.Category;
import codesquad.dto.category.ChildCategoryDto;
import codesquad.service.CategoryService;
import codesquad.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Category> createCategory(@RequestBody ChildCategoryDto childCategoryDto){
        return new CustomResponse<Category>(null,categoryService.addChild(childCategoryDto));
    }
}

package codesquad.controller;

import codesquad.domain.category.Category;
import codesquad.service.CategoryService;
import codesquad.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping()
    public CustomResponse<List<Category>> getCategoryList(){
        return new CustomResponse<List<Category>>(CustomResponse.MSG.OK, categoryService.getCategoryList());
    }
}

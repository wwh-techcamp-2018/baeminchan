package codesquad.web;


import codesquad.dto.CategoryListDto;
import codesquad.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping
    public CategoryListDto show() {
        return categoryService.getParents();
    }


}

package codesquad.product.web;

import codesquad.RestResponse;
import codesquad.product.domain.Category;
import codesquad.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public RestResponse<List<Category>> mainCategory() {
        return RestResponse.success(categoryService.getMainCategories());
    }
}

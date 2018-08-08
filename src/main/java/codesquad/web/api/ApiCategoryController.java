package codesquad.web.api;

import codesquad.domain.category.Category;
import codesquad.domain.product.Product;
import codesquad.service.CategoryService;
import codesquad.validation.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ApiCategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/categories")
    public Category save(@Valid @RequestBody Category category) {
        return categoryService.save(category);
    }

    @PostMapping("/admin/categories/{parentId}")
    public Category saveWithParent(@PathVariable Long parentId, @Valid @RequestBody Category category) {
        return categoryService.saveWithParent(parentId, category);
    }

    @GetMapping("/categories")
    public RestResponse<List<Category>> rootCategories() {
        return new RestResponse<>(categoryService.getRootCategories());
    }

    @GetMapping("/categories/{id}")
    public @ResponseBody
    Category category(@PathVariable Long id) {
        return categoryService.category(id);
    }

    @PutMapping("/admin/categories/{id}")
    public Category update(@PathVariable Long id, @Valid @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/admin/categories/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

    @GetMapping("/categories/{id}/products")
    public List<Product> categoryProduct(@PathVariable Long id) {
        return categoryService.findProductsByCategoryId(id);
    }
}

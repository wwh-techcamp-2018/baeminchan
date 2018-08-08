package codesquad.web.api;

import codesquad.domain.*;
import codesquad.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/categories")
@Api(value = "category", description = "Category Api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get banner titles")
    public List<Category> rootCategories() {
        return categoryService.findCategoriesByParent(null);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO fetchCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryDTOById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createUnderCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setParent(categoryService.findCategoryById(id));
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@PathVariable Long id, @RequestBody Category updateCategory) {
        return categoryService.update(id, updateCategory);
    }

}

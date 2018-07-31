package codesquad.web;

import codesquad.domain.*;
import codesquad.repository.CategoryRepository;
import codesquad.service.CategoryService;
import codesquad.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
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

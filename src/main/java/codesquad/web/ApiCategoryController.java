package codesquad.web;

import codesquad.domain.Category;
import codesquad.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ApiCategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/categories")
    public void save(@Valid @RequestBody Category category) {
        categoryService.save(category);
    }

    @PostMapping("/admin/categories/{parentId}")
    public void saveWithParent(@PathVariable Long parentId, @Valid @RequestBody Category category) {
        categoryService.saveWithParent(parentId, category);
    }

    @PutMapping("/admin/categories/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody Category category) {
        categoryService.update(id, category);
    }
}

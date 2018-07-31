package codesquad.web;

import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import codesquad.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> list() {
        return categoryService.getCategories();
    }

    @PostMapping(value = {"", "/{parentId}"})
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@PathVariable(required = false) Long parentId, @RequestBody  CategoryDto categoryDto) {
        return categoryService.create(categoryDto, parentId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category delete( @PathVariable Long id) {
        return categoryService.delete(null, id);
    }

}

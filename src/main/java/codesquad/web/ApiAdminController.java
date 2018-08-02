package codesquad.web;

import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import codesquad.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/categories")
public class ApiAdminController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(value = {"", "/{parentId}"})
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@PathVariable(required = false) Long parentId, @RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto, parentId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}

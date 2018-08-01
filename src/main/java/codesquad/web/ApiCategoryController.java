package codesquad.web;

import codesquad.domain.Category;
import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import codesquad.security.HttpSessionUtils;
import codesquad.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {
    private static final Logger log = LoggerFactory.getLogger(ApiCategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = {"", "/{parentId}"})
    public ResponseEntity<Category> create(@PathVariable(required = false) Long parentId, @RequestBody CategoryDto categoryDto, HttpSession session) {
        Category newCategory = categoryService.create(parentId, categoryDto, (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY));
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @GetMapping(value = {"", "/{parentId}"})
    public List<Category> list(@PathVariable(required = false) Long parentId) {
        return categoryService.getList(parentId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto, HttpSession session) {
        Category updatedCategory = categoryService.update(id, categoryDto, (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY));
        return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpSession session) {
        categoryService.delete(id, (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
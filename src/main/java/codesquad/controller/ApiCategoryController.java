package codesquad.controller;

import codesquad.domain.Category;
import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import codesquad.security.HttpSessionUtils;
import codesquad.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import static codesquad.security.HttpSessionUtils.getUserFromSession;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Resource
    private CategoryService categoryService;

    @GetMapping("")
    public Iterable<Category> list() {
        return categoryService.getParentList();
    }

    @PostMapping("")
    public ResponseEntity<Void> create(HttpSession session, @RequestBody CategoryDto categoryDto) {
        categoryService.addCategory(getUserFromSession(session), categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(HttpSession session, @PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.update(getUserFromSession(session), categoryDto, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(HttpSession session, @PathVariable Long id) {
        categoryService.delete(getUserFromSession(session), id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

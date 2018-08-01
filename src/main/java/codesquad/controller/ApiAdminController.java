package codesquad.controller;

import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import codesquad.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static codesquad.security.HttpSessionUtils.getUserFromSession;

@RestController
@RequestMapping("/api/admin/categories")
public class ApiAdminController {
    private static final Logger log = LoggerFactory.getLogger(ApiAdminController.class);

    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<Void> create(HttpSession session, @RequestBody CategoryDto categoryDto) {
        log.debug("ApiAdminController create : {}", getUserFromSession(session));
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

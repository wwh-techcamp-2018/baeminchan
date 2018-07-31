package codesquad.web;

import codesquad.domain.Category;
import codesquad.dto.CategoryDTO;
import codesquad.dto.UpdateCategoryDTO;
import codesquad.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.addChild(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Category category = categoryService.deleteById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory
            (@Valid @RequestBody UpdateCategoryDTO updateCategoryDTO, @PathVariable Long id) {
        Category category = categoryService.update(id, updateCategoryDTO);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}

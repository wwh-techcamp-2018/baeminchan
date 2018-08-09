package codesquad.web;

import codesquad.domain.Product;
import codesquad.dto.CategoryDto;
import codesquad.service.CategoryService;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @PostMapping("/categories")
    public ResponseEntity create(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryDto));
    }

    @DeleteMapping("/categories")
    public ResponseEntity delete(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/products")
    public ResponseEntity create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}

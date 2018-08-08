package codesquad.web.api;

import codesquad.domain.product.Product;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ApiProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/admin/product")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@Valid @RequestBody Product updateProduct) {
        return productService.update(updateProduct);
    }

    @DeleteMapping("/admin/product")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@Valid @RequestBody Product deleteProduct) {
        productService.delete(deleteProduct);
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product read(@PathVariable Long id) {
        return productService.read(id);
    }
}

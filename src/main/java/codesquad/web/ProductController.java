package codesquad.web;

import codesquad.domain.Product;
import codesquad.domain.RecommendationDTO;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/api/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody @Valid Product product) {
        return productService.create(product);
    }

    @GetMapping("/api/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product read(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @GetMapping("/search/recommendations")
    @ResponseStatus(HttpStatus.OK)
    public RecommendationDTO recommendations(@RequestParam String keyword) {
        return new RecommendationDTO(keyword, productService.findProductsContainingKeyword(keyword));
    }
}

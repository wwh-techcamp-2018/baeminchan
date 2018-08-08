package codesquad.web;

import codesquad.domain.Product;
import codesquad.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ApiProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/titles")
    public ResponseEntity<List<Product>> findAllTitles() {
        return new ResponseEntity<List<Product>>(productService.findAllTitles(), HttpStatus.OK);
    }
}

package codesquad.web;

import codesquad.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ApiProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/search/{keyword}")
    public ResponseEntity search(@PathVariable String keyword) {
        return ResponseEntity.ok().body(productService.search(keyword));
    }


}

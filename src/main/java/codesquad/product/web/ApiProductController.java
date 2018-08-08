package codesquad.product.web;

import codesquad.RestResponse;
import codesquad.product.domain.ProductIdAndTitle;
import codesquad.product.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ApiProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/search")
    public ResponseEntity<RestResponse> search(@RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return ResponseEntity.ok(RestResponse.of(Arrays.asList()));
        }
        List<ProductIdAndTitle> results =  productRepository.findTop10ByTitleContaining(keyword);
        return ResponseEntity.ok(RestResponse.of(results));
    }
}

package codesquad.web;

import codesquad.domain.Product;
import codesquad.service.ProductService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "모든 제품의 이름 조회", notes = "제품 검색 자동완성 ui 구현을 위해 호출합니다.")
    @GetMapping("/titles")
    public ResponseEntity<List<Product>> findAllTitles() {
        return new ResponseEntity<List<Product>>(productService.findAllTitles(), HttpStatus.OK);
    }
}

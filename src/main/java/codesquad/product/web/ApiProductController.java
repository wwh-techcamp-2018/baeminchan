package codesquad.product.web;

import codesquad.RestResponse;
import codesquad.category.domain.BestCategory;
import codesquad.category.domain.BestCategoryRepository;
import codesquad.product.domain.ProductIdAndTitle;
import codesquad.product.domain.ProductRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@RestController
public class ApiProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BestCategoryRepository bestCategoryRepository;

    @ApiOperation(value = "상품 검색 자동완성")
    @ApiImplicitParam(name = "keyword", value = "검색할 반찬의 이름", required = false, paramType = "string")
    @GetMapping("/products/search")
    public ResponseEntity<RestResponse> search(@RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return ResponseEntity.ok(RestResponse.of(Arrays.asList()));
        }
        List<ProductIdAndTitle> results =  productRepository.findTop10ByTitleContaining(keyword);
        return ResponseEntity.ok(RestResponse.of(results));
    }

    @ApiOperation(value = "(이벤트) 베스트 반찬 카테고리의 상품 목록 조회")
    @ApiImplicitParam(name = "id", value = "이벤트 반찬 카테고리 id", required = true, dataType = "long")
    @GetMapping("/category/best/{id}/products")
    @Cacheable(value="bestCategoryProducts")
    public ResponseEntity<RestResponse> getProductList(@PathVariable Long id) {
        BestCategory bestCategory = bestCategoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(RestResponse.of(bestCategory.getProducts()));
    }

}

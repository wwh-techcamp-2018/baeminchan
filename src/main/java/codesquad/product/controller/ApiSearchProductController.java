package codesquad.product.controller;

import codesquad.RestResponse;
import codesquad.product.domain.Product;
import codesquad.product.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class ApiSearchProductController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/{keyword}")
    public RestResponse<List<Product>> searchByKeyword(@PathVariable String keyword) {
        return RestResponse.success(searchService.searchByKeyword(keyword));
    }
}

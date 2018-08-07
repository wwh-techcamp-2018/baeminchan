package codesquad.controller;

import codesquad.domain.product.Product;
import codesquad.domain.search.SearchItem;
import codesquad.service.ProductService;
import codesquad.service.SearchItemService;
import codesquad.util.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ApiProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SearchItemService searchItemService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<Product>> list() {
        return new CustomResponse<>(CustomResponse.MSG.OK, productService.findAll());
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<List<SearchItem>> searchList() {
        return new CustomResponse<>(CustomResponse.MSG.OK, searchItemService.findAll());
    }
}

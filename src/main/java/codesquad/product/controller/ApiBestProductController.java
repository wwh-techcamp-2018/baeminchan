package codesquad.product.controller;

import codesquad.RestResponse;
import codesquad.product.domain.BestProduct;
import codesquad.product.domain.Product;
import codesquad.product.service.BestProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bestproducts")
public class ApiBestProductController {

    @Autowired
    private BestProductService bestProductService;

    @GetMapping("")
    public RestResponse<List<BestProduct>> getAllBestProduct() {
        return RestResponse.success(bestProductService.getAll());
    }

    @GetMapping("/{id}")
    public RestResponse<List<Product>> getProducts(@PathVariable Long id) {
        return RestResponse.success(bestProductService.getProducts(id));
    }
}

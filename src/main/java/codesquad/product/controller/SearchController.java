package codesquad.product.controller;

import codesquad.product.domain.ProductRepository;
import codesquad.support.dto.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/autocomplete")
    public ResponseModel<List<String>> search(@RequestParam("q") String data) {
        return ResponseModel.ofSuccess(productRepository.searchTitlesByKeyword(data), null);
    }
}

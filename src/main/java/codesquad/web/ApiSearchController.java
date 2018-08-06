package codesquad.web;

import codesquad.domain.Product;
import codesquad.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiSearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search/recommendations")
    public List<Product> searchRecommendations(@RequestParam String keyword) {
        return searchService.search(keyword);
    }
}

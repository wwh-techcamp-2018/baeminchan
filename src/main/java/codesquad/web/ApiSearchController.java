package codesquad.web;

import codesquad.dto.SearchRecommendationDto;
import codesquad.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiSearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search/recommendations")
    public SearchRecommendationDto searchRecommendations(@RequestParam String keyword) {
        return searchService.search(keyword);
    }
}

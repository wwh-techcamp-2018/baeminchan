package codesquad.service;

import codesquad.domain.product.ProductRepository;
import codesquad.dto.SearchRecommendationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    private ProductRepository productRepository;

    public SearchRecommendationDto search(String keyword) {
        return new SearchRecommendationDto(keyword, productRepository.findAllByTitleContains(keyword));
    }
}

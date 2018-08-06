package codesquad.service;

import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> search(String keyword) {
        return productRepository.findAllByTitleContains(keyword);
    }
}

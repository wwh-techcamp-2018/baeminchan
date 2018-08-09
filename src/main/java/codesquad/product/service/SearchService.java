package codesquad.product.service;

import codesquad.product.domain.Product;
import codesquad.product.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> searchByKeyword(String keyword) {
        return productRepository.findAllByTitleContaining(keyword);
    }
}

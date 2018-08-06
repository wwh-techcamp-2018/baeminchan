package codesquad.service;

import codesquad.domain.product.Product;
import codesquad.domain.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "products")
    public List<Product> findAll() {
        log.info("하...");
        return productRepository.findAll();
    }
}

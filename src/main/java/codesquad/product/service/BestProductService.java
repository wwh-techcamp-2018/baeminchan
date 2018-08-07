package codesquad.product.service;

import codesquad.product.domain.BestProduct;
import codesquad.product.domain.BestProductRepository;
import codesquad.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BestProductService {

    @Autowired
    private BestProductRepository bestProductRepository;

    @Cacheable("bestProductsTitle")
    public List<BestProduct> getAll() {
        return bestProductRepository.findAll();
    }

    @Cacheable("bestProducts")
    public List<Product> getProducts(Long bestProductId) {
        return bestProductRepository.findById(bestProductId)
                .orElseThrow(EntityNotFoundException::new)
                .getProducts();
    }
}

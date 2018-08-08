package codesquad.service;

import codesquad.domain.product.BestProduct;
import codesquad.domain.product.BestProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BestProductService {
    @Autowired
    private BestProductRepository bestProductRepository;

    @Cacheable(value = "products")
    public List<BestProduct> findAll() {
        return bestProductRepository.findAll();
    }
}

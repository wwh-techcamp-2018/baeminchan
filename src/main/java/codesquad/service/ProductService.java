package codesquad.service;

import codesquad.domain.Product;
import codesquad.exception.NotFoundException;
import codesquad.repository.ProductRepository;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(Long id) {
        return productRepository.findProductByIdEquals(id).orElseThrow(NotFoundException::new);
    }

    public List<Product> findProductsContainingKeyword(String keyword) {
        return productRepository.findAllByTitleContaining(keyword);
    }
}

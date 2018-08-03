package codesquad.service;

import codesquad.domain.DomainField;
import codesquad.domain.Product;
import codesquad.domain.ProductRepository;
import codesquad.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    @CacheEvict(value = "eventCategory", allEntries = true)
    public Product update(Product updateProduct) {
        existProduct(updateProduct.getId());
        return productRepository.save(updateProduct);
    }

    private void existProduct(Long id) {
        if(!productRepository.existsById(id)) {
            throw new BadRequestException(DomainField.ID, "존재하지 않는 상품입니다.");
        }
    }

    @CacheEvict(value = "eventCategory", allEntries = true)
    public void delete(Product deleteProduct) {
        existProduct(deleteProduct.getId());
        productRepository.delete(deleteProduct);
    }

    public Product read(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new BadRequestException(DomainField.ID, "존재하지 않는 상품입니다."));
    }
}

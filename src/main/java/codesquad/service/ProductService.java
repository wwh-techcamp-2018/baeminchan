package codesquad.service;

import codesquad.domain.category.Category;
import codesquad.domain.category.CategoryRepository;
import codesquad.domain.product.Product;
import codesquad.domain.product.ProductRepository;
import codesquad.dto.product.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Product add(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).get();
        Product product = Product.ofDto(productDto);
        category.addProduct(product);
        return productRepository.save(product);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).get();
        product.update(productDto);
        return productRepository.save(product);
    }
}

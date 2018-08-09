package codesquad.service;

import codesquad.domain.Product;
import codesquad.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<String> search(String keyword) {
        List<Product> products =  productRepository.findByNameLike("%" + keyword + "%");
        return products.stream().map(product -> product.getName()).collect(Collectors.toList());
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }



}

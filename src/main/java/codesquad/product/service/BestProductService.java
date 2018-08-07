package codesquad.product.service;

import codesquad.exception.ForbiddenException;
import codesquad.exception.ResourceNotFoundException;
import codesquad.product.domain.BestProduct;
import codesquad.product.domain.BestProductRepository;
import codesquad.product.domain.Product;
import codesquad.product.domain.ProductRepository;
import codesquad.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestProductService {
    @Autowired
    private BestProductRepository bestProductRepository;

    @Autowired
    private ProductRepository productRepository;

    public BestProduct add(BestProduct bestProduct) {
        return bestProductRepository.save(bestProduct);
    }

    public List<BestProduct> show() {
        return bestProductRepository.findAll();
    }

    public BestProduct delete(User maybeAdmin, Long bestProductId) {
        checkAdmin(maybeAdmin);
        BestProduct bestProduct = bestProductRepository.findById(bestProductId)
                .orElseThrow(() -> new ResourceNotFoundException("id", "존재하지 않는 베스트 반찬입니다."));
        bestProductRepository.delete(bestProduct);
        return bestProduct;
    }

    private void checkAdmin(User user) {
        if (!user.isAdmin()) {
            throw new ForbiddenException("관리자 권한이 필요합니다.");
        }

    }

    public List<Product> getProducts(Long id) {
        return bestProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id", "존재하지 않는 베스트 반찬입니다.")).getProducts();
    }
}

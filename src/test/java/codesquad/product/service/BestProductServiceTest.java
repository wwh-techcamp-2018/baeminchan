package codesquad.product.service;

import codesquad.product.domain.BestProduct;
import codesquad.product.domain.BestProductRepository;
import codesquad.product.domain.Product;
import codesquad.support.FixtureFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BestProductServiceTest {

    private static final Long BEST_PRODUCT_ID = 1L;

    @Mock
    private BestProductRepository bestProductRepository;

    @InjectMocks
    private BestProductService bestProductService;

    @Test
    public void findProductList() {
        BestProduct bestProduct = new BestProduct("Best Product", FixtureFactory.productList(3));
        when(bestProductRepository.findById(BEST_PRODUCT_ID)).thenReturn(Optional.of(bestProduct));

        List<Product> result = bestProductService.getProducts(BEST_PRODUCT_ID);

        assertThat(result).isEqualTo(bestProduct.getProducts());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findNotExist() {
        when(bestProductRepository.findById(BEST_PRODUCT_ID)).thenReturn(Optional.empty());

        bestProductService.getProducts(BEST_PRODUCT_ID);
    }
}

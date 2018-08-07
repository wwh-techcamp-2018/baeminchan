package codesquad.product.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BestProductCacheTest {
    @SpyBean
    private BestProductService bestProductService;

    @Test
    public void cacheHitBestProduct() {
        bestProductService.show();
        bestProductService.show();

        verify(bestProductService, times(1)).show();
    }

    @Test
    public void cacheHitProducts() {
        bestProductService.getProducts(1L);
        bestProductService.getProducts(1L);
        bestProductService.getProducts(2L);

        verify(bestProductService, times(1)).getProducts(1L);
        verify(bestProductService, times(1)).getProducts(2L);
    }
}

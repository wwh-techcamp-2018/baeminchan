package codesquad.product.service;

import codesquad.product.domain.BestProduct;
import codesquad.product.domain.BestProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BestProductCacheTest {

    @Autowired
    private BestProductRepository bestProductRepository;

    @SpyBean
    private BestProductService spyService;

    @Before
    public void setUp() throws Exception {
        bestProductRepository.deleteAll();
        bestProductRepository.save(new BestProduct("P1", new ArrayList<>()));
        bestProductRepository.save(new BestProduct("P2", new ArrayList<>()));
    }

    @Test
    public void getAllCacheHitTest() {
        spyService.getAll();
        spyService.getAll();
        verify(spyService, times(1)).getAll();
    }

    @Test
    public void getProductsCacheHitTest() {
        List<BestProduct> bestProducts = bestProductRepository.findAll();
        Long firstBestProductId = bestProducts.get(0).getId();
        Long secondBestProductId = bestProducts.get(1).getId();
        spyService.getProducts(firstBestProductId);
        spyService.getProducts(firstBestProductId);
        spyService.getProducts(secondBestProductId);
        verify(spyService, times(1)).getProducts(firstBestProductId);
        verify(spyService, times(1)).getProducts(secondBestProductId);
    }
}

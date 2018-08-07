package codesquad.product.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("development")
public class BestProductCacheTest {

    @SpyBean
    private BestProductService spyService;

    @Test
    public void getAllCacheHitTest() {
        spyService.getAll();
        spyService.getAll();
        verify(spyService, times(1)).getAll();
    }

    @Test
    public void getProductsCacheHitTest() {
        spyService.getProducts(1L);
        spyService.getProducts(1L);
        spyService.getProducts(2L);
        verify(spyService, times(1)).getProducts(1L);
        verify(spyService, times(1)).getProducts(2L);
    }
}

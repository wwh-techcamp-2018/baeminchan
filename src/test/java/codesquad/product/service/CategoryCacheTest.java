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
public class CategoryCacheTest {

    @SpyBean
    private CategoryService spyService;

    @Test
    public void getAllCacheHitTest() {
        spyService.getMainCategories();
        spyService.getMainCategories();
        verify(spyService, times(1)).getMainCategories();
    }

    @Test
    public void getCategoryCacheHitTest() {
        spyService.getSubCategories(1L);
        spyService.getSubCategories(1L);
        spyService.getSubCategories(2L);
        verify(spyService, times(1)).getSubCategories(1L);
        verify(spyService, times(1)).getSubCategories(2L);
    }
}

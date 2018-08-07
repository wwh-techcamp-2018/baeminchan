package codesquad.category.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryCacheTest {
    @SpyBean
    private CategoryService categoryService;

    @Test
    public void cacheHitTopCategory() {
        categoryService.getTopCategories();
        categoryService.getTopCategories();

        verify(categoryService, times(1)).getTopCategories();
    }

    @Test
    public void cacheHitSubCategory() {
        categoryService.getSubCategories(1L);
        categoryService.getSubCategories(1L);
        categoryService.getSubCategories(2L);

        verify(categoryService, times(1)).getSubCategories(1L);
        verify(categoryService, times(1)).getSubCategories(2L);
    }

}

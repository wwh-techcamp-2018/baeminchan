package codesquad.product.service;

import codesquad.product.domain.Category;
import codesquad.product.domain.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryCacheTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @SpyBean
    private CategoryService spyService;

    @Before
    public void setUp() throws Exception {
        categoryRepository.deleteAll();
        categoryRepository.save(Category.builder().title("C1").build());
        categoryRepository.save(Category.builder().title("C2").build());
    }

    @Test
    public void getAllCacheHitTest() {
        spyService.getMainCategories();
        spyService.getMainCategories();
        verify(spyService, times(1)).getMainCategories();
    }

    @Test
    public void getCategoryCacheHitTest() {
        Iterator<Category> categoryListIterator = categoryRepository.findAll().iterator();

        Long firstCategoryId = categoryListIterator.next().getId();
        Long secondCategoryId = categoryListIterator.next().getId();
        spyService.getSubCategories(firstCategoryId);
        spyService.getSubCategories(firstCategoryId);
        spyService.getSubCategories(secondCategoryId);
        verify(spyService, times(1)).getSubCategories(firstCategoryId);
        verify(spyService, times(1)).getSubCategories(secondCategoryId);
    }
}

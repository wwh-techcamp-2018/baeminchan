package codesquad.product.service;

import codesquad.product.domain.Category;
import codesquad.product.domain.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void getMainCategories() {
        categoryService.getMainCategories();
        verify(categoryRepository).findByParentCategoryId(null);
    }


    @Test
    public void getSubCategories() {
        Category mainCategory = Category.builder().id(1L).build();
        when(categoryRepository.findById(mainCategory.getId())).thenReturn(Optional.of(mainCategory));

        categoryService.getSubCategories(mainCategory.getId());
        verify(categoryRepository).findByParentCategoryId(mainCategory.getId());
    }
}
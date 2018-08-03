package codesquad.service;

import codesquad.domain.Category;
import codesquad.exception.NotFoundException;
import codesquad.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    private Category category;
    private Category updateCategory;

    @InjectMocks
    private CategoryService categoryService;

    @Before
    public void setUp() throws Exception {
        category = new Category("test category", null);
        category.setId(0L);

        updateCategory = new Category("updated category", null);
        updateCategory.setId(0L);

        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryRepository.findCategoryById(0L)).thenReturn(Optional.of(category));
    }

    @Test
    public void save() {
        categoryService.save(new Category());
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    public void update() {
        assertThat(categoryService.update(0L, updateCategory)).isEqualTo(category);
    }

    @Test(expected = NotFoundException.class)
    public void updateFailed() {
        Category fakeCategory = new Category("fake", null);
        fakeCategory.setId(1000L);
        categoryService.update(1000L, fakeCategory);
    }
}

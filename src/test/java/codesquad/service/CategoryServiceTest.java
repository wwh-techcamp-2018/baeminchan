package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.exception.BadRequestException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category originalCategory;

    @Before
    public void setUp() throws Exception {
        originalCategory = Category.valueOf(1L, "맛반");
    }

    @Test
    public void save() {
        categoryService.save(originalCategory);
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    public void rootCategories() {
        categoryService.getRootCategories();
        verify(categoryRepository, times(1)).findByParentNull();
    }

    @Test
    public void update() {
        when(categoryRepository.findById((long) 1)).thenReturn(Optional.ofNullable(originalCategory));
        categoryService.update((long) 1, Category.valueOf("update Category"));
        verify(categoryRepository, times(1)).save(any());
    }

    @Test(expected = BadRequestException.class)
    public void assertExistCategory() {
        when(categoryRepository.findById((long) 1)).thenReturn(Optional.empty());
        categoryService.update((long) 1, Category.valueOf("update Category"));
    }


    @Test
    public void delete() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(originalCategory));
        categoryService.delete(1L);
        verify(categoryRepository, times(1)).findById(any());
        verify(categoryRepository, times(1)).delete(any());
    }
}
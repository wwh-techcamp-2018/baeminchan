package codesquad.product.service;

import codesquad.product.domain.Category;
import codesquad.product.domain.CategoryRepository;
import codesquad.product.dto.CategoryDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private Category mainCategory;

    @Before
    public void setUp() throws Exception {
        mainCategory = Category.builder().id(1L).build();
    }

    @Test
    public void getMainCategories() {
        categoryService.getMainCategories();
        verify(categoryRepository).findAllByParentCategory(null);
    }


    @Test
    public void getSubCategories() {
        when(categoryRepository.findById(mainCategory.getId())).thenReturn(Optional.of(mainCategory));

        categoryService.getSubCategories(mainCategory.getId());
        verify(categoryRepository).findAllByParentCategory(mainCategory);
    }

    @Test
    public void createMainCategory() {
        CategoryDto categoryDto = CategoryDto.builder().title("new Category").build();
        Category category = Category.builder().title(categoryDto.getTitle()).build();

        when(categoryRepository.save(category)).thenReturn(category);

        Category savedCategory = categoryService.create(categoryDto);

        assertThat(savedCategory.getTitle()).isEqualTo(category.getTitle());
        assertThat(savedCategory.getParentCategory()).isNull();
    }


    @Test
    public void createSubCategory() {
        CategoryDto categoryDto = CategoryDto.builder()
                .title("new Child Category")
                .parentCategoryId(mainCategory.getId())
                .build();


        Category category = Category.builder().title(categoryDto.getTitle())
                .parentCategory(mainCategory).build();

        when(categoryRepository.findById(mainCategory.getId())).thenReturn(Optional.of(mainCategory));
        when(categoryRepository.save(category)).thenReturn(category);

        Category savedCategory = categoryService.create(categoryDto);

        assertThat(savedCategory.getTitle()).isEqualTo(categoryDto.getTitle());
        assertThat(savedCategory.getParentCategory()).isEqualTo(mainCategory);
    }
}
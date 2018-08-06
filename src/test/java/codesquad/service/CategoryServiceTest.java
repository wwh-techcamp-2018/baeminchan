package codesquad.service;

import codesquad.domain.Category;
import codesquad.repository.CategoryRepository;
import codesquad.dto.CategoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
    public static final Logger log =  LoggerFactory.getLogger(MemberServiceTest.class);
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void createTest() {
        CategoryDto categoryDto = CategoryDto.defaultCategoryDto();
        categoryService.save(categoryDto);
        verify(categoryRepository).save(categoryDto.toEntity());
    }

    @Test
    public void findByNameTest() {
        Category category = CategoryDto.defaultCategoryDto().toEntity();
        when(categoryRepository.findByName(category.getName())).thenReturn(Optional.ofNullable(category));
        assertThat(categoryService.findByName(category.getName())).isEqualTo(category);
    }
}
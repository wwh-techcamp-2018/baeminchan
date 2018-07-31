package codesquad.category.service;

import codesquad.category.domain.Category;
import codesquad.category.domain.CategoryRepository;
import codesquad.category.dto.CategoryDto;
import codesquad.exception.BadRequestException;
import codesquad.exception.ForbiddenException;
import codesquad.exception.ResourceNotFoundException;
import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CategoryServiceTest {


    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;
    private User user;
    private CategoryDto dto;

    @Before
    public void setUp() throws Exception {
        user = User.builder().role(Role.USER).build();
        dto = new CategoryDto("밥", null);
    }

    @Test(expected = BadRequestException.class)
    public void parentCategoryNotExist() {
        user.setRole(Role.ADMIN);
        dto.setParentCategoryId(Long.MAX_VALUE);
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        categoryService.create(user, dto);
    }

    @Test(expected = ForbiddenException.class)
    public void notAdmin() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        categoryService.create(user, dto);
    }

    @Test
    public void delete() {
        Category category = new Category("밥", null);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        user.setRole(Role.ADMIN);
        assertThat(categoryService.delete(user, 1L)).isEqualTo(category);
    }

    @Test(expected = ForbiddenException.class)
    public void deleteNotAdmin() {
        Category category = new Category("밥", null);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        categoryService.delete(user, 1L);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteNotExist() {
        when(categoryRepository.findById(Long.MAX_VALUE)).thenReturn(Optional.empty());

        user.setRole(Role.ADMIN);
        categoryService.delete(user, Long.MAX_VALUE);
    }
}

package codesquad.service;

import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import codesquad.dto.CategoryListDto;
import codesquad.dto.CategoryPostDto;
import codesquad.repository.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private CategoryListDto categoryListDto;
    private List<Category> categoryList;
    private Category parent1;
    private Category parent2;
    private Category parent3;
    private Category child1;
    private Category child2;

    @Before
    public void setUp() throws Exception {
        parent1 = new Category("parent1");
        parent2 = new Category("parent2");
        parent3 = new Category("parent3");
        child1 = new Category("child1");
        child2 = new Category("child2");
        parent1.addChild(child1).addChild(child2);
        categoryList = Arrays.asList(parent1, parent2, parent3);

        CategoryDto parentDto1 = new CategoryDto("parent1");
        CategoryDto parentDto2 = new CategoryDto("parent2");
        CategoryDto parentDto3 = new CategoryDto("parent3");
        CategoryDto childDto1 = new CategoryDto("child1");
        CategoryDto childDto2 = new CategoryDto("child2");
        parentDto1.setChildren(Arrays.asList(childDto1, childDto2));
        categoryListDto = new CategoryListDto(Arrays.asList(parentDto1, parentDto2, parentDto3));


    }

    @Test
    public void getParents() {
        when(categoryRepository.findByParentIsNull()).thenReturn(categoryList);
        assertThat(categoryService.getParents()).isEqualTo(categoryListDto);
    }

    @Test
    public void add() {
        Category mockParentCategory = new Category("국찌개");
        //TODO 최상위 카테고리를 검색하고 있는 것임 : 카테고리 트리의 깊이가 2 level 이상이면 변경해야 함.
        when(categoryRepository.findByNameAndParentIsNull("국찌개")).thenReturn(Optional.of(mockParentCategory));

        CategoryPostDto categoryPostDto = new CategoryPostDto("국찌개", "담백한국");
        categoryService.add(categoryPostDto);

        Category parentCategory = new Category("국찌개");
        Category category = new Category("담백한국");
        parentCategory.addChild(category);
        verify(categoryRepository).save(category);
    }

    @Test
    public void delete() {
        when(categoryRepository.findByName("child1")).thenReturn(Arrays.asList(child1));

        CategoryPostDto categoryPostDto = new CategoryPostDto("parent1", "child1");
        categoryService.delete(categoryPostDto);

        Category childToBeDeleted = new Category("child1");
        verify(categoryRepository).delete(childToBeDeleted);
    }
}

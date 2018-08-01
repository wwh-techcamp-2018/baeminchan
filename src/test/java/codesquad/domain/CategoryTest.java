package codesquad.domain;

import codesquad.dto.CategoryDTO;
import codesquad.dto.UpdateCategoryDTO;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {
    private Category parentCategory;
    private CategoryDTO childCategoryDTO;

    @Before
    public void setUp() throws Exception {
        parentCategory = new Category("아빠");
        childCategoryDTO = new CategoryDTO();
        childCategoryDTO.setTitle("나");
    }

    @Test
    public void addChildTest() {
        parentCategory.addChild(childCategoryDTO);

        Category childCategory = parentCategory.getChildren().get(0);
        assertThat(childCategory.getTitle().equals("나")).isTrue();
        assertThat(childCategory.getParent().getTitle().equals("아빠")).isTrue();
    }

    @Test
    public void deleteTest_하위() {
        parentCategory.delete();
        assertThat(parentCategory.isDeleted()).isTrue();
    }

    @Test
    public void deleteTest_상위() {
        parentCategory.addChild(childCategoryDTO);
        parentCategory.delete();
        assertThat(parentCategory.isDeleted()).isTrue();
        assertThat(parentCategory.getChildren().get(0).isDeleted()).isTrue();
    }

    @Test
    public void updateTest() {
        parentCategory.update(new UpdateCategoryDTO(null,"변경"));
        assertThat(parentCategory.getTitle()).isEqualTo("변경");
    }
}
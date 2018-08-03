package codesquad.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryDTOTest extends DTOTest<CategoryDTO> {
    @Test
    public void createTest_성공() {
        CategoryDTO categoryDTO = new CategoryDTO(3L, "타이틀");
        assertThat(isValid(categoryDTO)).isTrue();
    }

    @Test
    public void createTest_실패() {
        CategoryDTO categoryDTO = new CategoryDTO(3L, "");
        assertThat(isValid(categoryDTO)).isFalse();
    }
}
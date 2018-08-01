package codesquad.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateCategoryDTOTest extends DTOTest<UpdateCategoryDTO> {
    @Test
    public void createTest_성공() {
        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO(2L,"타이틀이다");
        assertThat(isValid(updateCategoryDTO)).isTrue();
    }

    @Test
    public void createTest_실패() {
        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO(2L,"");
        assertThat(isValid(updateCategoryDTO)).isFalse();
    }
}
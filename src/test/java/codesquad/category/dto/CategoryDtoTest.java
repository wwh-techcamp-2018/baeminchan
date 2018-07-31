package codesquad.category.dto;

import codesquad.support.test.DtoValidationTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class CategoryDtoTest extends DtoValidationTest<CategoryDto> {
    @Test
    public void nullTitle() {
        CategoryDto dto = new CategoryDto(null, null);
        validate(dto, 1);
    }

    @Test
    public void emptyStringTitle() {
        CategoryDto dto = new CategoryDto("", null);
        validate(dto, 1);
    }

    @Test
    public void maxStringTitle() {
        CategoryDto dto = new CategoryDto(StringUtils.repeat("a", 101), null);
        validate(dto, 1);
    }
}

package codesquad.product.dto;

import codesquad.product.domain.Product;
import codesquad.support.test.DtoValidationTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BestProductDtoTest extends DtoValidationTest<BestProductDto> {
    @Mock
    private List<Product> products;

    @Test
    public void validateDefault() {
        BestProductDto dto = new BestProductDto("서울 맛집 탐방", products);
        validate(dto, 0);
    }

    @Test
    public void validateNull() {
        BestProductDto dto = new BestProductDto("서울 맛집 탐방", null);
        validate(dto, 0);
    }

    @Test
    public void validateEmptyName() {
        BestProductDto dto = new BestProductDto("", products);
        validate(dto, 1);
    }

    @Test
    public void validateNullName() {
        BestProductDto dto = new BestProductDto(null, products);
        validate(dto, 1);
    }

    @Test
    public void validateMinName() {
        BestProductDto dto = new BestProductDto("1", products);
        validate(dto, 0);
    }

    @Test
    public void validateMaxName() {
        BestProductDto dto = new BestProductDto(StringUtils.repeat('a',30), products);
        validate(dto, 0);
    }

    @Test
    public void validateExceedNameLength() {
        BestProductDto dto = new BestProductDto(StringUtils.repeat('a',31), products);
        validate(dto, 1);
    }
}

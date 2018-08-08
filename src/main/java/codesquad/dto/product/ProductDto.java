package codesquad.dto.product;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Long price;
    private String imgUrl;
    private Long categoryId;
}

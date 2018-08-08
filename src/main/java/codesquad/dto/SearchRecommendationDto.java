package codesquad.dto;

import codesquad.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchRecommendationDto {

    private String keyword;

    private List<Product> products;
}

package codesquad.dto;


import codesquad.domain.BestCategory;
import codesquad.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class BestCategoryDto implements Serializable {

    private String categoryName;
    private List<Product> products;
    public static BestCategoryDto fromEntity(BestCategory bestCategory) {
        return new BestCategoryDto(bestCategory.getCategoryName(), bestCategory.getProducts());
    }

    public static BestCategoryDto defaultBestCategoryDto() {
        return new BestCategoryDto("오늘은 뭐먹지", Arrays.asList(Product.defaultProduct(), Product.defaultProduct()));
    }

    public BestCategory toEntity() {
        return new BestCategory(this.categoryName, this.products);
    }

    public BestCategoryDto setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public BestCategoryDto setProducts(List<Product> products) {
        this.products = products;
        return this;
    }
}

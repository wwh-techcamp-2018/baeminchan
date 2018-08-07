package codesquad.dto.product;

import codesquad.domain.product.Product;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductSearchDTO {
    private Long id;
    private String title;

    public ProductSearchDTO() {}
    public ProductSearchDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static List<ProductSearchDTO> getSearchDTOList(List<Product> products) {
        List<ProductSearchDTO> productSearchDTOs = new ArrayList<>();
        products.forEach(product -> {
            productSearchDTOs.add(product.toSearchDTO());
        });
        return productSearchDTOs;
    }
}

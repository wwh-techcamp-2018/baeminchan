package codesquad.product.dto;

import codesquad.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@NoArgsConstructor
@Getter
public class BestProductDto {

    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    private List<Product> products;

    public BestProductDto(@NotNull @NotEmpty @Size(min = 1, max = 30) String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }
}

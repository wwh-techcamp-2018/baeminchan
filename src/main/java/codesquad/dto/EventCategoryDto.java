package codesquad.dto;

import codesquad.domain.category.EventCategory;
import codesquad.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EventCategoryDto implements Serializable {

    private EventCategory eventCategory;

    private List<Product> products;
}

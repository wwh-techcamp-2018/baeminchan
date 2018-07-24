package codesquad.domain.product;

import java.util.List;

public class Category {
    private Long id;
    private String title;
    private Category parentCategory;
    private List<Product> products;
}

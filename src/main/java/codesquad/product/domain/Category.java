package codesquad.product.domain;

import java.util.List;

public class Category {
    private Long id;
    private String title;
    private Category parentCategory;
    List<Product> products;
}

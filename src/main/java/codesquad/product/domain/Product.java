package codesquad.product.domain;

import java.util.Set;

public class Product {
    private Long id;
    private Category category;
    private String title;
    private Long originalPrice;
    private Double discountPercent;
    private String description;
    private Set<String> images;
    private String deliveryType;
    private Set<Day> receiptableDays;
}

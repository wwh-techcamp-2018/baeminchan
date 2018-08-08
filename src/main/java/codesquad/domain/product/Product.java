package codesquad.domain.product;

import codesquad.dto.product.ProductDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 2)
    private String title;

    @Column
    @Size(min = 2)
    private String description;

    @Column
    private Long price;

    @Column
    @Size(min = 2)
    private String imgUrl;

    public Product(@Size(min = 2) String title, @Size(min = 2) String description, Long price, @Size(min = 2) String imgUrl) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public static Product ofDto(ProductDto productDto) {
        return new Product(productDto.getTitle(), productDto.getDescription(), productDto.getPrice(), productDto.getImgUrl());
    }

    public void update(ProductDto productDto) {
        this.title = productDto.getTitle();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.imgUrl = productDto.getImgUrl();
    }
}

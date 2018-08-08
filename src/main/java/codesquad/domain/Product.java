package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NoArgsConstructor
public class Product implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String title;

    @Getter
    @Setter
    @NotBlank
    @Size(min = 1)
    @Column(nullable = false)
    private String contents;

    @Getter
    @Setter
    @NotNull
    @Column(nullable = false)
    private Integer price;

    @Getter
    @Setter
    @NotBlank
    @Column(nullable = false)
    private String thumbnailLink;

    @Getter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;

    @Getter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private EventCategory eventCategory;

    @Builder
    public Product(Long id, String title, String contents, Integer price, String thumbnailLink) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.price = price;
        this.thumbnailLink = thumbnailLink;
    }

    public String generateUrl() {
        return "/product/" + id;
    }

}

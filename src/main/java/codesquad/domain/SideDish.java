package codesquad.domain;

import codesquad.enums.DeliveryType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
public class SideDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_brand"))
    private Brand brand;

    @NonNull
    @Column
    private String name;

    @NonNull
    @Column
    @ColumnDefault("0")
    private int price;
    @NonNull
    @Column
    @ColumnDefault("0")
    private int salePrice;
    @NonNull
    @Column
    private String description;
    @NonNull
    @Column
    @ColumnDefault("0")
    private int weight;
    @Column
    private String enableDay;
    @Column
    private DeliveryType deliveryType;

    @Column
    @ColumnDefault("false")
    private boolean isEnableRegularDelivery;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SideDishImage> images;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_category"))
    private Category category;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_side_writer"))
    private User writer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SideDish sideDish = (SideDish) o;
        return price == sideDish.price &&
                salePrice == sideDish.salePrice &&
                weight == sideDish.weight &&
                Objects.equals(name, sideDish.name) &&
                Objects.equals(description, sideDish.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, price, salePrice, description, weight);
    }
}


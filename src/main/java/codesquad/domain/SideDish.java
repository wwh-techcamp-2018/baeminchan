package codesquad.domain;

import codesquad.enums.DeliveryType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString
public class SideDish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_side_dish_brand"))
    private Brand brand;

    private String name;
    private int price;
    private int salePrice;
    private String description;
    private int weight;
    private String enableDay;

    @Enumerated(value = EnumType.STRING)
    private DeliveryType deliveryType;
    private boolean isEnableRegularDelivery;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_category"))
    private Category category;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_side_writer"))
    private User writer;


}

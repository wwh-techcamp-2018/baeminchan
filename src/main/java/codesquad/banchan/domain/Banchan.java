package codesquad.banchan.domain;

import codesquad.bestcategory.domain.BestCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banchan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subTitle;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private int originalPrice;

    @Column(nullable = false)
    private int salesRate;

    @ManyToMany(mappedBy = "banchans")
    private List<Badge> badges;

    @JsonIgnore
    @ManyToMany(mappedBy = "banchans")
    private List<BestCategory> bestCategorys;
}

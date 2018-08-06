package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString
public class BestCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_best_category_writer"))
    private User writer;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "best_category_side_dish",
            joinColumns = @JoinColumn(name = "best_category_id"),
            inverseJoinColumns = @JoinColumn(name = "side_dish_id"))
    @JsonIgnore
    private List<SideDish> sideDishes;
}
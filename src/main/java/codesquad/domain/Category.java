package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_parent_id"))
    private Category parentCategory;

    @Column
    private String name;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private List<Category> subCategories = new ArrayList<>();
}

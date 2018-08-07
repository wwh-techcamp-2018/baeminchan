package codesquad.bestcategory.domain;

import codesquad.banchan.domain.Banchan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BestCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="tbl_bestcategory_banchan", joinColumns={@JoinColumn(name="bestcategory_id")}, inverseJoinColumns={@JoinColumn(name="banchan_id")})
    private List<Banchan> banchans;
}

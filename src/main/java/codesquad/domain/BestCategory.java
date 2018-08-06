package codesquad.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Where(clause = "deleted != 'true'")
public class BestCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SideDish> sideDishes;

    @Column(name = "deleted")
    @ColumnDefault("false")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_best_writer"))
    private User writer;

    @Column
    private LocalDateTime createTime;

    @Override
    public String toString() {
        return "BestCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}



package codesquad.domain;


import codesquad.support.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BestCategory extends AbstractEntity {
    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Banchan> banchans = new ArrayList<>();

    @Column(nullable = false)
    private boolean isActive = true;
}

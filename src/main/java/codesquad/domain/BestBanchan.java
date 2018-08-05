package codesquad.domain;


import codesquad.support.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BestBanchan extends AbstractEntity {
    private String title;

    @OneToMany(mappedBy = "parent")
    //todo * FetchLazy 나중에 하기
    @JsonIgnore
    private List<Banchan> banchans;
}

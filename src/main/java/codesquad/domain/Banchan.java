package codesquad.domain;

import codesquad.support.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Banchan extends AbstractEntity {
    //todo * BestBanchan --> 다른 상위클래스/IF로 바꾸고, 다대다 고려
    @ManyToOne
    private BestBanchan parent;
    private String imgUrl;
    private String title;
    private String description;
    //todo int형식으로 바꾼다?
    private String originalPrice;
    private String realPrice;
}

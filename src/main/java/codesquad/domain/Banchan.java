package codesquad.domain;

import codesquad.support.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Banchan extends AbstractEntity {
    //todo * BestBanchan --> 다른 상위클래스/IF로 바꾸고, 다대다 고려
    @ManyToOne
    private BestCategory parent;

    @NotNull
    private String imgUrl;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 50)
    private String title;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 10, max = 500)
    private String description;

    private Integer originalPrice;

    @Column(nullable = false)
    @NotBlank
    private Integer realPrice;
}



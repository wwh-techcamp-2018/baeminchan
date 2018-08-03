package codesquad.domain.promotion;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor
public class Promotion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long PromotionId;
    @Size(max = 100)
    @Column(nullable = false)
    private String PromotionUrl;
}

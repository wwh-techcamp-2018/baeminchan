package codesquad.promotion.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    public Promotion(Long id, String imgUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
    }
}

package codesquad.domain;

import codesquad.dto.PromotionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static codesquad.utils.Utils.PRIORITY_DEFAULT;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Where(clause = "deleted != 'true'")
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    @NotNull
    private String userId;

    @Column
    @ColumnDefault(PRIORITY_DEFAULT)
    private int priority;

    @Column
    @NotNull
    private LocalDateTime createdTime;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    @NotNull
    private String imageUrl;

    @Column(name = "deleted")
    @ColumnDefault("false")
    private boolean isDeleted;

    public Promotion(String title, String userId, int priority, LocalDate startDate, LocalDate endDate, String imageUrl) {
        this.title = title;
        this.userId = userId;
        this.priority = priority;
        this.createdTime = LocalDateTime.now();
        this.startDate = startDate;
        this.endDate = endDate;
        this.imageUrl = imageUrl;
    }

    public void update(PromotionDto promotionDto, User loginUser) {
        this.title = promotionDto.getTitle();
        this.userId = loginUser.getUserId();
        this.priority = promotionDto.getPriority();
        this.startDate = promotionDto.getStartDate();
        this.endDate = promotionDto.getEndDate();
        this.imageUrl = promotionDto.getImageUrl();
    }

    public void delete() {
        this.isDeleted = true;
    }
}

/*

  todo list
  - promotion 테스트 코드 작성
  - list, create, delete, update 컨트롤러 구현
  - index html 템플릿 작성
  - 프론트엔드 js 및 css 구현

  */







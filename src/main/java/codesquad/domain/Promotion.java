package codesquad.domain;

import codesquad.dto.PromotionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@NoArgsConstructor
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
    @ColumnDefault("5")
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

    @Column
    @ColumnDefault("false")
    private boolean deleted;

    public Promotion(String title, int priority, LocalDate startDate, LocalDate endDate) {
        checkValidPeriod(startDate, endDate);
        this.title = title;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Promotion(String title, String userId, int priority, LocalDate startDate, LocalDate endDate, String imageUrl) {
        this(title, priority, startDate, endDate);
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.createdTime = LocalDateTime.now();
    }

    public void delete() {
        deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Promotion update(PromotionDto updatedPromotionDto) {
        return new Promotion(
                updatedPromotionDto.getTitle(),
                updatedPromotionDto.getPriority(),
                updatedPromotionDto.getStartDate(),
                updatedPromotionDto.getEndDate()
        );
    }

    public void checkValidPeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new ValidationException("기간 형식이 맞지 않습니다.");
        }
    }
}
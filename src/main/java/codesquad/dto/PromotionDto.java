package codesquad.dto;

import codesquad.domain.Promotion;
import codesquad.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PromotionDto {
    private String title;
    private int priority;
    private LocalDate startDate;
    private LocalDate endDate;
    private MultipartFile imageFile;

    public PromotionDto(String title, int priority, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Promotion toPromotion(User sessionUser) {
        // TODO : 추후 S3 와 연동해 파일에 대한 url을 받아와야 한다.
        String tempUrl = "https://cdn.bmf.kr/banner/main_banner/180724/1532409236448_d9b2dde93f6a.jpg";
        return new Promotion(title, sessionUser.getUserId(), priority, startDate, endDate, tempUrl);
    }
}

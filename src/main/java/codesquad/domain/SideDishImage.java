package codesquad.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class SideDishImage {

    @Id
    private Long id;

    private String imageURL;

    private LocalDateTime createdTime;
}
package codesquad.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SideDishImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageURL;

    @Column
    private LocalDateTime createdTime;
}

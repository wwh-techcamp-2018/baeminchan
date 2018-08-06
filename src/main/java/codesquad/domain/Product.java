package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 2)
    private String imgUrl;

    @Column
    private int price;

    @Column
    @Size(min = 2, max = 50)
    private String title;

    @Column
    @Size(min = 2)
    private String discribe;

    @ManyToOne
    @JsonIgnore
    private TabCategory parent;
}

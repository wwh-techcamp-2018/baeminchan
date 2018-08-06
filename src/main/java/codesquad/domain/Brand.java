package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<SideDish> sideDishes;

    private String address;
}

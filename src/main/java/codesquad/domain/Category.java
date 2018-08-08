package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@NoArgsConstructor
@ToString
public class Category implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @NotBlank
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String title;

    @Setter
    @JsonIgnore
    @ManyToOne
    private Category parent;

    @Getter
    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Category> children;


    private Category(Long id, String title) {
        this.id = id;
        this.title = title;
    }


    public static Category valueOf(String title) {
        return valueOf(null, title);
    }

    public static Category valueOf(Long id, String title) {
        return new Category(id, title);
    }

    public void update(Category updateCategory) {
        title = updateCategory.title;
    }
}

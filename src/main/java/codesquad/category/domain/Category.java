package codesquad.category.domain;

import codesquad.category.dto.CategoryDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@ToString(exclude = {"parent", "children"})
@EqualsAndHashCode(exclude = {"parent", "children"})
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    public static final Long ROOT = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Category> children;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean deleted;

    public void update(CategoryDto categoryDto) {
        this.name = categoryDto.getName();
    }

    public void delete() {
        this.deleted = true;
    }
}

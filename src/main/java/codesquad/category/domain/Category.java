package codesquad.category.domain;

import codesquad.exception.UnAuthorizedException;
import codesquad.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor
public class Category {

    public static final Long ROOT_ID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @JsonIgnore
    @ManyToOne
    @NonNull
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL})
    @OrderBy("id ASC")
    private Set<Category> children;

    public void update(User user, String name, Category parent) {
        if (!user.isAdmin()) {
            throw new UnAuthorizedException("role", "권한이 없습니다.");
        }
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name +
                "', children size=" + children.size() +
                " }";
    }
}

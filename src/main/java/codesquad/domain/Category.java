package codesquad.domain;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 30)
    private String title;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="parentId")
    private List<Category> children;

    @Column
    private boolean deleted = false;

}

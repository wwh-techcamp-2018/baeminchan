package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_parent_id"))
    private Category parentCategory;

    @Column
    private String name;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    @OrderBy("priority ASC")
    private List<Category> subCategories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_creator_id"))
    private User creator;

    @Column
    private LocalDateTime createdTime;

    @Column
    @ColumnDefault("5")
    private int priority;

    public Category() {
    }

    public Category(Category parentCategory, String name, User creator, LocalDateTime createdTime, int priority) {
        this.parentCategory = parentCategory;
        this.name = name;
        this.creator = creator;
        this.createdTime = createdTime;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Category{" +
                "Id=" + Id +
                ", parentCategory=" + parentCategory +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", createdTime=" + createdTime +
                ", priority=" + priority +
                '}';
    }
}

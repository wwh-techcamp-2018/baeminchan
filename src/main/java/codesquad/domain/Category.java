package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

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
    @Where(clause = "deleted = false")
    private List<Category> subCategories = new ArrayList<>();
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_creator_id"))
    private User creator;
    @Column
    private LocalDateTime createdTime;
    @Column
    @ColumnDefault("false")
    private boolean deleted;
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

    public boolean isOwner(User user) {
        return this.creator.equals(user);
    }

    private void delete() {
        this.deleted = true;
    }

    public void deleteAll() {
        this.deleted = true;
        if(this.parentCategory == null) {
            deleteChildren();
        }
    }

    private void deleteChildren() {
        for (Category subCategory : subCategories) {
            subCategory.delete();
        }
    }

    public void update(Category category) {
        this.parentCategory = category.parentCategory;
        this.name = category.name;
        this.priority = category.priority;
    }

    @Override
    public String toString() {
        return "Category{" +
                "Id=" + Id +
                ", name='" + name +
                ", creator=" + creator +
                ", createdTime=" + createdTime +
                ", priority=" + priority +
                '}';
    }
}

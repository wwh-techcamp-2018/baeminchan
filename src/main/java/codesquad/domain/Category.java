package codesquad.domain;

import codesquad.dto.CategoryDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Where(clause = "deleted != 'true'")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_category_parent")) // index 테이블 이름
    private Category parentCategory;

    @Column
    private String name;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> subCategories;

    @Column(name = "deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_category_writer"))
    private User writer;

    @Column
    private LocalDateTime createTime;

    @Column
    @ColumnDefault("5")
    private int priority;

    public Category(Category parentCategory, String name) {
        this.parentCategory = parentCategory;
        this.name = name;
        this.createTime = LocalDateTime.now();
    }

    public Category update(CategoryDto categoryDto) {
        this.name = categoryDto.getName();
        return this;
    }

    public void delete() {
        this.isDeleted = true;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }

}
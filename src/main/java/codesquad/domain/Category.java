package codesquad.domain;

import codesquad.support.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Category extends AbstractEntity {
    public static final RootCategory ROOT = new RootCategory();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children;

    @NotBlank
    private String name;

    public Category() {
        children = new ArrayList<>();
    }
    public Category(String name, Category parent){
        this();
        this.name = name;
        this.parent = parent;
    }

    public boolean isNotRootCategory(Long rootId) {
        return this.getId() != rootId;
    }

    public boolean isRoot(){
        return false;
    }

    private static class RootCategory extends Category{
        final Long rootId = 0L ;
        public RootCategory() {
            this.setId(rootId);
        }

        @Override
        public boolean isRoot() {
            return true;
        }
    }

}

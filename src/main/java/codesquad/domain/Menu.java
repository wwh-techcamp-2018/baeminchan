package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Menu{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min =2, max = 12)
    @NotNull
    private String name;

    @ManyToOne
    @JsonIgnore
    @Nullable
    private Menu parent;

    @Column
    @Nullable
    private int index;

    @OneToMany(mappedBy = "parent")
    @Nullable
    @OrderBy("index ASC")
    private Set<Menu> children = new HashSet<>();

    @Column
    @Nullable
    private int childCount;

    public Menu() {
    }

    public Menu(String name) {
        this(name, null, null, 0,0);
    }

    public Menu(String name, Menu parent) {
        this(name, parent, null, 0, 0);
    }

    public Menu(String name, Menu parent, Set<Menu> children, int index, int childCount) {
        this.name = name;
        this.parent = parent;
        this.children = children;
        this.index = index;
        this.childCount = childCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Set<Menu> getChildren() {
        return children;
    }

    public void setChildren(Set<Menu> children) {
        this.children = children;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", index=" + index +
                ", childCount=" + childCount +
                '}';
    }
}

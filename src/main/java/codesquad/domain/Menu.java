package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Menu implements Serializable {

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
    private List<Menu> children = new ArrayList<>();

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

    public Menu(String name, Menu parent, List<Menu> children, int index, int childCount) {
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

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
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

    public boolean hasChild(){
        return childCount != 0;
    }

    public boolean hasParent(){
        return parent != null;
    }

    public void registChild(Menu child){
        child.index = childCount + 1;
        children.add(child);
        childCount += 1;
    }

    public void delete(){
       parent.deleteChild(this);
    }

    public void deleteChild(Menu child){
        children.stream().filter(c -> c.index > child.index).forEach(c -> c.decreaseIndex());
        children.remove(child);
        childCount -= 1;
    }

    private void increaseIndex(){
        index += 1;
    }

    private void decreaseIndex(){
        index -= 1;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", index=" + index +
                ", childCount=" + childCount +
                '}';
    }
}

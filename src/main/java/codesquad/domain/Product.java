package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    String title;

    @NotBlank
    @Size(min = 1)
    @Column(nullable = false)
    String contents;

    @NotNull
    @Column(nullable = false)
    Integer price;

    @NotBlank
    @Column(nullable = false)
    String thumbnailLink;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Category category;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    EventCategory eventCategory;

    public Product(){

    }

    public Product(String title, String contents, Integer price, String thumbnailLink) {
        this.title = title;
        this.contents = contents;
        this.price = price;
        this.thumbnailLink = thumbnailLink;
    }

    public Product(Long id, String title, String contents, Integer price, String thumbnailLink) {
        this(title, contents, price, thumbnailLink);
        this.id = id;
    }

    public String generateUrl() {
        return "/product/" + id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }
}

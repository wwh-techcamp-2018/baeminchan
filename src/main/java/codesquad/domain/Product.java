package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JsonIgnore
    private Menu eventMenu;

    @ManyToOne
    @JsonIgnore
    private Menu menu;


    public Product(long id, int price, String thumbnail, String title, String description, Menu eventMenu, Menu menu) {
        this.id = id;
        this.price = price;
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
        this.eventMenu = eventMenu;
        this.menu = menu;
    }

}
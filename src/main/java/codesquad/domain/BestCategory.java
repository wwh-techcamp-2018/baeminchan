package codesquad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


/**TODO
 * 1. 백엔드
 *  - spring chache 적용
 *
 *  Done
 *  - 베스트 카테고리 리스트 보내주기
 *  - 베스트 카테고리 아이디에 따른 반찬 보내주기
 *
 *
 * 2. 프론트엔드
 *  - 불필요한 AJAX 요청 하지 않도록 구현
 *
 *  Done
 *  - 베스트 카테고리 리스트 요청
 *  - 클릭한 베스트 카테고리의 반찬 요청
 */


@Entity
@Getter
@ToString
public class BestCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_best_category_writer"))
    private User writer;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "best_category_side_dish",
                joinColumns = @JoinColumn(name = "best_category_id"),
                inverseJoinColumns = @JoinColumn(name = "side_dish_id"))
    @JsonIgnore
    private List<SideDish> sideDishes;
}
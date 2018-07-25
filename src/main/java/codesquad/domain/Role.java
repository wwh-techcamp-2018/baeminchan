package codesquad.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private boolean read;

    @Column()
    private boolean write;

    @Column()
    private boolean update;

    @Column()
    private boolean delete;
}

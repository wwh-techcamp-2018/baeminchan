package codesquad.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean read;

    private boolean write;

    private boolean update;

    private boolean delete;
}

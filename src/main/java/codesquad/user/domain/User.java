package codesquad.user.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role =Role.DEFAULT;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean deleted;

}

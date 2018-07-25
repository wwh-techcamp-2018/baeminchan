package codesquad.domain;


//import codesquad.validator.Email;
import codesquad.validator.Email;
import codesquad.validator.UserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Map;

@Entity
@Data
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Builder.Default
    private Long id;

    @Column()
    @Email()
    private String email;

    @Column()
    @UserName
    private String name;

    @Column()
    private String password;

    @Transient
    private String confirmPassword;

    @Column()
    private String phone;

//    @Column()
//    private Map<String, Role> roles;

    @Column()
    @Builder.Default
    private boolean deleted = false;

    public User() {
    }

    public User(String email, String name, String password, String confirmPassword, String phone) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phone = phone;
    }
}

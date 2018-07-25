package codesquad.domain;


//import codesquad.validator.Email;
import codesquad.validator.Email;
import codesquad.validator.Password;
import codesquad.validator.Phone;
import codesquad.validator.UserName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Map;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

//    @Column()
//    private Map<String, Role> roles;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean deleted;

}

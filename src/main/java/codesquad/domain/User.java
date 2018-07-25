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
    @Builder.Default
    private Long id;

    private String email;

    private String name;

    private String password;

    private String phone;

//    @Column()
//    private Map<String, Role> roles;

    @Column
    @Builder.Default
    private boolean deleted = false;

}

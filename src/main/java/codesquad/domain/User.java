package codesquad.domain;

import codesquad.validate.Email;
import codesquad.validate.Name;
import codesquad.validate.Password;
import codesquad.validate.Phone;
import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
//    String patternPhone = "(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Email
    private String email;

    @NonNull
    @Name
    private String name;

    @NonNull
    @Password
    private String password;

    @Transient
    private String confirmPassword;


    @NonNull
    @Phone
    private String phone;

    private Map<String, Role> roles;

    private boolean deleted;

}

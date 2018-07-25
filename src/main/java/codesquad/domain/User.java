package codesquad.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String name;

    @NonNull
    private String password;

    @NonNull
    private String phone;

    //private Map<String, Role> roles;

    private boolean deleted;


}

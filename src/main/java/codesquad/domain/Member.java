package codesquad.domain;

import codesquad.dto.MemberDto;
import codesquad.support.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;
    @Column(length = 30, nullable = false)
    private String username;
    @Column(length = 11, nullable = false)
    private String phoneNumber;
    @ElementCollection
    private Set<Role> roles = new HashSet<>(Arrays.asList(Role.DEFAULT));

    public Member(String email, String password, String username, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }
    public static Member fromDto(MemberDto inputMemberDto, PasswordEncoder bCryptPasswordEncoder) {
        MemberDto memberDto = new MemberDto(inputMemberDto);
        memberDto.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        return memberDto.toEntity();

    }
}

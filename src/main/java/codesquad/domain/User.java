package codesquad.domain;

import codesquad.dto.JoinUserDTO;
import codesquad.exception.ValidationException;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String encryptedPassword;
    @NotNull
    private String name;
    @NotNull
    private String phoneNo;
    @Column
    private boolean isAdmin = false;

    public User() {
    }

    public User(@NotNull String email, @NotNull String encryptedPassword, @NotNull String name, @NotNull String phoneNo) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public static User fromDTO(JoinUserDTO joinUserDTO, PasswordEncoder passwordEncoder) {
        validateFields(joinUserDTO);
        return new User(joinUserDTO.getEmail(), passwordEncoder.encode(joinUserDTO.getPassword()),
                joinUserDTO.getName(), joinUserDTO.getPhoneNo());
    }

    // TODO: 2018. 7. 24. 유효성 검사를 누적해서 하나만 만들어서 한번에 예외 처리합니다.
    // 이메일: 길이 체크하기 (32), 특수문자 포함 X, 뒤에 도메인이 .이 들어있는지 확인 하나만 들어있어함
    // 비밀번호: 비밀번호와 비밀번호 확인이 같아야. 숫자, 문자 섞여있어야함, 8자~ 16자 길이 체크
    // 이름: 길이 체크(32)
    // 핸드폰번호: 모두 숫자인지 확인
    private static void validateFields(JoinUserDTO joinUserDTO) {
    }

    protected void changeAdmin() {
        isAdmin = true;
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, encryptedPassword);
    }
}

package codesquad.dto;

import codesquad.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    @Size(min = 3, max = 200)
    private String email;

    @Size(min = 7, max = 200)
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-zA-Z]).{8,15})", message = "Invalid Password")
    private String password;

    @Size(min = 3, max = 30)
    private String username;

    @Size(min = 9, max = 11)
    private String phoneNumber;

    public Member toMember() {
        return new Member(email, password, username, phoneNumber);
    }

}

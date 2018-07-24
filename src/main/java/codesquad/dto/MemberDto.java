package codesquad.dto;


import codesquad.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String email;
    private String password;
    private String username;
    private String phoneNumber;

    public Member toEntity() {
        //TODO: password encryption should be added
        return new Member(email, password, username, phoneNumber);
    }

}

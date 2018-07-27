package codesquad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDto {
    @NotBlank(message = "please input email")
    @Email(message = "wrong email format")
    private String email;

    @NotBlank(message = "please input password")
    private String password;
}

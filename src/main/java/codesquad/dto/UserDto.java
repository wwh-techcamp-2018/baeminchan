package codesquad.dto;

import codesquad.validator.Email;
import codesquad.validator.Password;
import codesquad.validator.Phone;
import codesquad.validator.UserName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Email
    private String email;

    @UserName
    private String name;

    @Password
    private String password;

    @Password
    @JsonProperty("password-check")
    private String confirmPassword;

    @Phone
    private String phone;

}

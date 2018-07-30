package codesquad.user.dto;

import codesquad.utils.Regex;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Pattern(regexp = Regex.EMAIL, message = "Invalid email")
    private String email;

    @Pattern(regexp = Regex.USER_NAME, message = "Invalid name")
    private String name;

    @Pattern(regexp = Regex.PASSWORD, message = "Invalid password")
    private String password;

    @Pattern(regexp = Regex.PASSWORD, message = "Invalid password")
    @JsonProperty("password-check")
    private String confirmPassword;

    @Pattern(regexp = Regex.PHONE, message = "Invalid phone")
    private String phone;

}

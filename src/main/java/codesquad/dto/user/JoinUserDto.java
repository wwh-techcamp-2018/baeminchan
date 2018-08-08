package codesquad.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class JoinUserDto {

    private static final String PASSWORD_PATTERN = "^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,12}$";

    @Pattern(regexp = "^[_0-9a-zA-Z-]+@[0-9a-zA-Z]+(.[_0-9a-zA-Z-]+)*$", message = "올바른 이메일을 입력하세요.")
    @ApiModelProperty(value = "email", dataType = "string", required = true)
    private String email;

    @Pattern(regexp = PASSWORD_PATTERN, message = "숫자 영문자 특수 문자를 포함한 8 ~ 12 자를 입력하세요.")
    @ApiModelProperty(value = "password", dataType = "string", required = true)
    private String password;

    @ApiModelProperty(value = "passwordConfirm", dataType = "string", required = true)
    private String passwordConfirm;

    @Size(min = 2)
    @ApiModelProperty(value = "name", dataType = "string", required = true)
    private String name;

    @Pattern(regexp = "^01[0|1|6-9][0-9]{7,8}$", message = "올바른 휴대폰번호를 입력하세요.")
    @ApiModelProperty(value = "phoneNo", dataType = "string", required = true)
    private String phoneNo;

    public JoinUserDto() {
    }

    @ApiModelProperty(hidden = true, readOnly = true)
    @AssertTrue(message = "비밀번호와 비밀번호확인이 다릅니다.")
    public boolean isConfirmPassword() {
        return password.equals(passwordConfirm);
    }

}

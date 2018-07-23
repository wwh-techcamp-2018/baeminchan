package codesquad.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginUserDto {

    @ApiModelProperty(value = "email", dataType = "string", required = true)
    private String email;

    @ApiModelProperty(value = "password", dataType = "string", required = true)
    private String password;

}

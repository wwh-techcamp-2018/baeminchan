package codesquad.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JoinUserDto {

    @ApiModelProperty(value = "email", dataType = "string", required = true)
    private String email;

    @ApiModelProperty(value = "password", dataType = "string", required = true)
    private String password;

    @ApiModelProperty(value = "passwordConfirm", dataType = "string", required = true)
    private String passwordConfirm;

    @ApiModelProperty(value = "name", dataType = "string", required = true)
    private String name;

    @ApiModelProperty(value = "phoneNo", dataType = "string", required = true)
    private String phoneNo;



}

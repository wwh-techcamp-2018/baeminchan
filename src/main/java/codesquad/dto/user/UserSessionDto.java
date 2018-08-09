package codesquad.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionDto {
    @ApiModelProperty(value = "uid", dataType = "Long")
    private Long uid;

    @ApiModelProperty(value = "isAdmin", dataType = "boolean")
    private boolean isAdmin;
}

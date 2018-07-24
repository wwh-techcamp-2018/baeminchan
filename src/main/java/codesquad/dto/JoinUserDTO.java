package codesquad.dto;

import lombok.Data;

@Data
public class JoinUserDTO {
    private String email;
    private String password;
    private String passwordConfirm;
    private String name;
    private String phoneNo;
}

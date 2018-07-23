package codesquad.dto;

import lombok.Data;

@Data
public class JoinUserDto {

    private String email;
    private String password;
    private String passwordConfirm;
    private String name;
    private String phoneNo;



}

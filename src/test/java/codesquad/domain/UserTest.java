package codesquad.domain;

import codesquad.dto.user.JoinUserDto;

public class UserTest {
    public static final JoinUserDto CYS = new JoinUserDto("chldbtjd2272@naver.com","password!2","password!2","유성","01035442272");


    public static JoinUserDto createDefaultUser(){
        JoinUserDto joinUserDto = new JoinUserDto("chldbtjd2272@naver.com","password!2","password!2","유성","01035442272");
        return joinUserDto;
    }
}

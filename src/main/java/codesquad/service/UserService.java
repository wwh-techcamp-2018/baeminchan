package codesquad.service;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.JoinUserDto;
import codesquad.dto.LoginUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("userService")
public class UserService {

    @Resource(name = "userRepository")
    UserRepository userRepository;

    public void add(JoinUserDto joinUserDto){
        userRepository.save(User.createUserByJoinUserDto(joinUserDto));
    }

    public User login(LoginUserDto loginUserDto) {
        User user = userRepository.findByEmail(loginUserDto.getEmail()).get();
        user.isMatchPassword(loginUserDto);
        return user;
    }
}

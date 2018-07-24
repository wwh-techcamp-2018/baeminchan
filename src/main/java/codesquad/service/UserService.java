package codesquad.service;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.JoinUserDto;
import codesquad.dto.LoginUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("userService")
public class UserService {

    @Resource(name = "userRepository")
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User add(JoinUserDto joinUserDto) {
        return userRepository.save(User.createUserByJoinUserDto(joinUserDto, passwordEncoder));
    }

    public User login(LoginUserDto loginUserDto) {
        User user = userRepository.findByEmail(loginUserDto.getEmail()).get();
        user.isMatchPassword(loginUserDto);
        return user;
    }
}

package codesquad.service;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.JoinUserDto;
import codesquad.dto.LoginUserDto;
import codesquad.exception.AlreadyExistsUserException;
import codesquad.exception.LoginFailedException;
import codesquad.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLException;
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
        checkExistsEmail(joinUserDto.getEmail());
        return userRepository.save(User.createUserByJoinUserDto(joinUserDto, passwordEncoder));
    }

    public User login(LoginUserDto loginUserDto) {
        try {
            User user = findByEmail(loginUserDto.getEmail());
            user.isMatchPassword(loginUserDto, passwordEncoder);
            return user;
        }catch (UserNotFoundException e){
            throw new LoginFailedException();
        }
    }

    private void checkExistsEmail(String email){
        try {
            findByEmail(email);
        }catch (UserNotFoundException e){
            return;
        }
        throw new AlreadyExistsUserException();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}

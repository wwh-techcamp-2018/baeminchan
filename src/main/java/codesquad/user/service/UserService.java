package codesquad.user.service;

import codesquad.exception.UnAuthenticationException;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.dto.LoginDto;
import codesquad.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User save(UserDto userDto) throws UnAuthenticationException {
        if (!userDto.matchPassword()) {
            // TODO fix fieldName hard-coding..
            throw new UnAuthenticationException("password", "비밀번호가 일치하지 않습니다.");
        }

        return userRepository.save(userDto.toEntity(passwordEncoder));
    }

    public User login(LoginDto loginDto) throws UnAuthenticationException {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UnAuthenticationException("email", "email이 존재하지 않습니다."));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new UnAuthenticationException("password", "비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}

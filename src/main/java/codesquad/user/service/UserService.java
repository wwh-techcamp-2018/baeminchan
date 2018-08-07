package codesquad.user.service;

import codesquad.exception.UnAuthenticationException;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.dto.LoginDto;
import codesquad.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class UserService {

    public static final String USER_SESSION_KEY = "loginId";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User save(UserDto newUserDto) {
        User newUser = User.builder()
                .email(newUserDto.getEmail())
                .name(newUserDto.getName())
                .password(encoder.encode(newUserDto.getPassword()))
                .phone(newUserDto.getPhone())
                .build();

        log.debug("body: {}", newUser);
        return userRepository.save(newUser);
    }

    public User login(LoginDto loginDto) {
        User maybeUser = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(UnAuthenticationException::invalidEmail);
        if (!encoder.matches(loginDto.getPassword(), maybeUser.getPassword())) {
            throw UnAuthenticationException.invalidPassword();
        }
        log.info("-------------------user: {}", maybeUser);
        return maybeUser;
    }

    public void logout(HttpSession httpSession) {
        httpSession.removeAttribute(USER_SESSION_KEY);
    }

}

package codesquad.service;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.LoginDto;
import codesquad.dto.UserDto;
import codesquad.exception.UnAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class UserService {

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

    public void login(LoginDto loginDto, HttpSession httpSession) {
        User maybeUser = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(EntityNotFoundException::new);
        if (!encoder.matches(loginDto.getPassword(), maybeUser.getPassword())) {
            throw UnAuthenticationException.invalidPassword();
        }
        httpSession.setAttribute("loginedUser", maybeUser.getId());
    }
}

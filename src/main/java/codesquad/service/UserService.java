package codesquad.service;

import codesquad.BadRequestException;
import codesquad.domain.DomainField;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.LoginDto;
import codesquad.dto.SignupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service("userService")
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Resource(name = "passwordEncoder")
    PasswordEncoder passwordEncoder;

    @Resource(name = "userRepository")
    UserRepository userRepository;

    void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(SignupDto signupDto) {
        Optional<User> maybeUser = userRepository.findByEmail(signupDto.getEmail());
        if (maybeUser.isPresent())
            throw new BadRequestException(DomainField.USER_EMAIL.getFieldName(), "이미 존재하는 아이디입니다.");

        return userRepository.save(User.of(signupDto, passwordEncoder));
    }

    public User login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new BadRequestException(DomainField.USER_EMAIL.getFieldName(), "존재하지 않는 아이디입니다."));
        user.checkPassword(loginDto.getPassword(), passwordEncoder);
        return user;
    }

}

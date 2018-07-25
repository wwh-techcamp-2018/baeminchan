package codesquad.service;

import codesquad.domain.Authority;
import codesquad.domain.RoleRepository;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.UserDto;
import codesquad.exception.NotMatchException;
import codesquad.exception.UnAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(String userId, String password) throws UnAuthenticationException {
        return userRepository.findByUserId(userId)
                .filter(user -> user.matchPassword(password))
                .orElseThrow(() -> new UnAuthenticationException("아이디 혹은 비밀번호가 맞지 않습니다."));
    }

    public void create(UserDto user) throws NotMatchException {
        User newUser = user.toUser(passwordEncoder.encode(user.getPassword()));
        newUser.init(roleRepository.findByAuthority(Authority.NORMAL).orElseThrow(IllegalArgumentException::new));
        userRepository.save(newUser);
    }
}

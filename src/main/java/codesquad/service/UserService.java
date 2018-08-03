package codesquad.service;

import codesquad.domain.*;
import codesquad.dto.UserDto;
import codesquad.exception.UnAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void join(UserDto userDto) {
        Role role = roleRepository.findById(1L).get();
        userRepository.save(userDto.toUser(role, passwordEncoder));
    }

    public User login(String userId, String password) throws UnAuthenticationException {
        User savedUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UnAuthenticationException("아이디와 비밀번호가 일치하지 않습니다."));
        savedUser.login(password, passwordEncoder);
        return savedUser;
    }
}

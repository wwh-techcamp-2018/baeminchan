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
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(UserDto userDto) throws UnAuthenticationException {
        return userRepository.findByUserId(userDto.getUserId())
                .filter(user -> passwordEncoder.matches(userDto.getPassword(), user.getPassword()))
                .orElseThrow(() -> new UnAuthenticationException("아이디 혹은 비밀번호가 맞지 않습니다."));
    }

    @Transactional
    public void create(UserDto userDto) throws NotMatchException {
        userRepository.save(userDto.toUser(passwordEncoder.encode(userDto.getPassword()),
                roleRepository.findByAuthority(Authority.NORMAL).get()));
    }
}

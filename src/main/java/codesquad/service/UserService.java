package codesquad.service;

import codesquad.domain.Authority;
import codesquad.domain.RoleRepository;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.exception.UnAuthenticationException;
import codesquad.web.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User login(String userId, String password) throws UnAuthenticationException {
        return userRepository.findByUserId(userId)
                .filter(user -> user.matchPassword(password))
                .orElseThrow(UnAuthenticationException::new);
    }

    public void create(User user) {
        user.init(roleRepository.findByAuthority(Authority.NORMAL).orElseThrow(IllegalArgumentException::new));
        userRepository.save(user);
    }
}

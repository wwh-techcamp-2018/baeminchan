package codesquad.user.service;

import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User add(User user) {
        user.encodePassword(passwordEncoder);
        return userRepository.save(user);
    }
}

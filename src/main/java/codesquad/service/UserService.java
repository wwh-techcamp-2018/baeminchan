package codesquad.service;

import codesquad.domain.Role;
import codesquad.domain.RoleRepository;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public User login(String userId, String password) throws IllegalAccessException {
        User savedUser = userRepository.findByUserId(userId).orElseThrow(IllegalAccessException::new);
        savedUser.login(password, passwordEncoder);
        return savedUser;
    }
}

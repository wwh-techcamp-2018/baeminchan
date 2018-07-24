package codesquad.service;

import codesquad.domain.User;
import codesquad.dto.JoinUserDTO;
import codesquad.dto.LoginUserDTO;
import codesquad.exception.LoginFailedException;
import codesquad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginUserDTO join(JoinUserDTO joinUserDTO) {
        userRepository.save(User.fromDTO(joinUserDTO, passwordEncoder));
        return new LoginUserDTO(joinUserDTO.getEmail(), joinUserDTO.getPassword());
    }

    public User login(LoginUserDTO loginUserDTO) {
        return userRepository.findByEmail(loginUserDTO.getEmail())
                .filter(u -> u.matchPassword(passwordEncoder, loginUserDTO.getPassword()))
                .orElseThrow(LoginFailedException::new);
    }
}

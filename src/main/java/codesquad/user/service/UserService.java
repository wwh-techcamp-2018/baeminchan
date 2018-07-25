package codesquad.user.service;

import codesquad.exception.ConflictException;
import codesquad.exception.UnauthenticatedException;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.dto.LoginDto;
import codesquad.user.dto.SignupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User signup(SignupDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictException("email", "이메일");
        }

        if (userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new ConflictException("phoneNumber", "전화번호");
        }

        return userRepository.save(dto.toEntity(passwordEncoder));
    }

    @Transactional(readOnly = true)
    public User login(LoginDto dto) {
        return userRepository.findByEmail(dto.getEmail())
                .filter(user -> user.matchPassword(dto.getPassword(), passwordEncoder))
                .orElseThrow(UnauthenticatedException::new);
    }
}

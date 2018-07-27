package codesquad.user.service;

import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.exception.UnAuthenticationException;
import codesquad.constant.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSourceAccessor msa;

    public User add(User user) {
        user.encodePassword(passwordEncoder);
        return userRepository.save(user);
    }

    public void login(User user) {
        User loginUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UnAuthenticationException(msa.getMessage(Message.UNAUTHENTICATED)));
        if (!loginUser.matchPassword(user, passwordEncoder)) {
            throw new UnAuthenticationException(msa.getMessage(Message.WRONG_PASSWORD));
        }
    }

}

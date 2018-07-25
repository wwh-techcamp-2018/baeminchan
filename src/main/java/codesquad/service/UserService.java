package codesquad.service;

import codesquad.domain.User;
import codesquad.repository.UserRepository;
import codesquad.support.NotExistException;
import org.aspectj.weaver.BCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    public User create(User newUser) {
        if(!newUser.isEqualPassword())
            throw new IllegalArgumentException("입력된 비밀번호가 일치하지 않습니다.");
       //1.  user,setPassword ( String e.encode())
                // 2. bc. encodeUser(User) -> user.updateP (encrtpyePW)
        //newUser.setPassword(encoder.encode(newUser.getPassword()));
        newUser.encrypt(encoder);
        //newUser.encrypt();
        return userRepository.save(newUser);
    }
    public User login(String email, String rawPassword){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotExistException("ID / PW 를 확인해주십시오"));
        if(!user.matchEncodedPassword(encoder, rawPassword))
            throw new NotExistException("ID / PW 를 확인해주십시오");
        return user;
    }
}

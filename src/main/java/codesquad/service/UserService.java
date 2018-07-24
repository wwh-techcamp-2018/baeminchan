package codesquad.service;

import codesquad.domain.User;
import codesquad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User create(User newUser) {
        if(!newUser.isEqualPassword())
            throw new IllegalArgumentException("입력된 비밀번호가 일치하지 않습니다.");
        return userRepository.save(newUser);
    }

    public User find(long id){
        return userRepository.findById(id).get();
    }
}

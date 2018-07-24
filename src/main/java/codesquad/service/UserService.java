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
       return userRepository.save(newUser);
    }

    public User find(long id){
        return userRepository.findById(id).get();
    }
}

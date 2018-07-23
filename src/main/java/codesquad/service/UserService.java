package codesquad.service;

import codesquad.domain.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserService {

    @Resource(name = "userRepository")
    UserRepository repository;
}

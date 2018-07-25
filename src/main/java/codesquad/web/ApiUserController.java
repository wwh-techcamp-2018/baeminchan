package codesquad.web;

import codesquad.domain.User;
import codesquad.domain.UserDto;
import codesquad.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class ApiUserController {

    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserDto userDto) {
        User savedUser = userRepository.save(userDto.toEntity(passwordEncoder));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/users/" + savedUser.getId()));
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}

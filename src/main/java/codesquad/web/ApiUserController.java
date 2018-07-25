package codesquad.web;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/users")
public class ApiUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserDto newUserDto) {
        User newUser = User.builder()
                .email(newUserDto.getEmail())
                .name(newUserDto.getName())
                .password(encoder.encode(newUserDto.getPassword()))
                .phone(newUserDto.getPhone())
                .build();

        log.debug("body: {}", newUser);
        User user = userRepository.save(newUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/users" + user.getId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}

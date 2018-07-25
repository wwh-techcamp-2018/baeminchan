package codesquad.web;

import codesquad.domain.LoginDto;
import codesquad.domain.User;
import codesquad.domain.UserDto;
import codesquad.domain.UserRepository;
import codesquad.security.HttpSessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/users")
public class ApiUserController {

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

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginDto, HttpSession session) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseGet(null); // TODO exception

        if (!loginDto.matchPassword(user, passwordEncoder)) {
            // TODO error exception
            log.info("not match password !");
        }

        HttpSessionUtils.setUserSessionKey(session, user);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }
}

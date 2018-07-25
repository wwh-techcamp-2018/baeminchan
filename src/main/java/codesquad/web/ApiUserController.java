package codesquad.web;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class ApiUserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody User newUser){
        User user = userRepository.save(newUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/users" + user.getId()));
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

}

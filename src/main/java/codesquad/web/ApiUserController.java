package codesquad.web;


import codesquad.domain.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class ApiUserController {

//    @PostMapping
//    public ResponseEntity<Void> create(User user) {
//
//
//        User savedUser = userRepository.save(user);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create("/users/" + savedUser.getId()));
//        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//    }
}

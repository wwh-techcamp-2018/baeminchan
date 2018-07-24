package codesquad.user.web;

import codesquad.RestResponse;
import codesquad.user.domain.User;
import codesquad.user.dto.UserSignupDto;
import codesquad.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<RestResponse<?>> create(@RequestBody UserSignupDto dto) {
        User user = userService.add(dto.toEntity());

        if (!dto.matchPassword()) {
            // TODO: change hardcoded message
            return ResponseEntity.badRequest().body(
                    RestResponse.error("password", "not match password").build()
            );
        }

        return ResponseEntity.created(URI.create(String.format("/api/users/%s", user.getUuid()))).build();
    }
}

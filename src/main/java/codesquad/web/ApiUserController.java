package codesquad.web;

import codesquad.domain.User;
import codesquad.service.UserService;
import codesquad.support.ErrorResponse;
import codesquad.support.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);
    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@Valid @RequestBody User user, @RequestBody String repeatPassword){
        log.debug("repeatPassword {}", repeatPassword);
        log.debug("newUser {}", user);
        if(repeatPassword == null || repeatPassword.equals(user.getPassword())){
            ErrorResponse response = new ErrorResponse();
            response.registErrorMessage("비밀번호 확인이 달라여");
            return response;
        }
        userService.create(user);
        return new JsonResponse("/");
    }
}

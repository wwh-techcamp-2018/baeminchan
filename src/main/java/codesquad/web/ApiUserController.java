package codesquad.web;

import codesquad.domain.User;
import codesquad.support.JsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    private static final Logger log = LoggerFactory.getLogger(ApiUserController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JsonResponse create( User newUser){
         //header - status
        //body - location
        log.debug("newUser {}", newUser);
        return new JsonResponse("/");
    }

}

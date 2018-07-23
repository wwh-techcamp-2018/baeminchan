package codesquad.web;

import codesquad.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource(name = "userService")
    private UserService service;
}

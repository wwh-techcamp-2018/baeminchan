package codesquad.admin.web;

import codesquad.category.domain.Category;
import codesquad.category.domain.CategoryRepository;
import codesquad.exception.UnAuthenticationException;
import codesquad.security.HttpSessionUtils;
import codesquad.user.domain.User;
import codesquad.user.dto.LoginDto;
import codesquad.user.dto.UserDto;
import codesquad.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/admin")
public class ApiAdminController {

    @Resource(name = "userService")
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginDto, HttpSession session) throws UnAuthenticationException {
        User user = userService.loginAdmin(loginDto);
        HttpSessionUtils.setUserSessionKey(session, user);

//        Category category = categoryRepository.findByParentIsNull()
//                .orElseThrow(EntityNotFoundException::new);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        HttpSessionUtils.removeSessionID(session);
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.OK);
    }
}

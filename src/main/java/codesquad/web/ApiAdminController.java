package codesquad.web;

import codesquad.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {
    private static final Logger log = LoggerFactory.getLogger(ApiAdminController.class);

    @Autowired
    UserService userService;

    @GetMapping("")
    public void mainPage(HttpSession httpSession){

    }

    @PostMapping("/login")
    public void adminLogin(@RequestBody Map<String, String> admin, HttpSession httpSession){
        log.debug("Admin controller call, admin : {}", admin.toString());
        return ;
    }
}

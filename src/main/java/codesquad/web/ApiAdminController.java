package codesquad.web;

import codesquad.repository.MenuRepository;
import codesquad.service.MenuService;
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

    @Autowired
    MenuService menuService;

    @GetMapping("")
    public void mainPage(){

    }

    @DeleteMapping("/menu/{menuId}")
    public void deleteSpecificMenu(@PathVariable long menuId){
        menuService.deleteMenu(menuId);
    }

    @PostMapping("/menu")
    public void registSpecificMenu(@RequestBody Map<String, String> menu){
        long parentId = Long.parseLong(menu.get("parentId"));
        String childMenuName = menu.get("childMenuName");
        log.debug("requestBody : {} {}", parentId, childMenuName);
        menuService.registMenu(parentId, childMenuName);
    }
}

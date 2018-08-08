package codesquad.web;

import codesquad.domain.Menu;
import codesquad.repository.MenuRepository;
import codesquad.service.MenuService;
import codesquad.support.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class ApiMenuController {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuService menuService;

    @GetMapping("")
    public Menu allMenu(){
        return menuService.getRootMenu();
    }

    @GetMapping("/{id}")
    public Menu specificMenu(@PathVariable long id){
        return menuService.getSpecificMenu(id);
    }
}

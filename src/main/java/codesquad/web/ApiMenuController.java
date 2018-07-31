package codesquad.web;

import codesquad.domain.Menu;
import codesquad.repository.MenuRepository;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class ApiMenuController {

    @Autowired
    MenuRepository menuRepository;

    @GetMapping("")
    public Menu allMenu(){
        return menuRepository.findById((long)1).get();
    }
}

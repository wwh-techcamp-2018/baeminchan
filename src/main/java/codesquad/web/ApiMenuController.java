package codesquad.web;

import codesquad.domain.Menu;
import codesquad.repository.MenuRepository;
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

    @GetMapping("")
    public Menu allMenu(){
        return menuRepository.findByParentIsNull().orElseThrow(() -> new NotExistException("최상단 메뉴 존재하지 않음"));
    }

    @GetMapping("/{id}")
    public Menu specificMenu(@PathVariable long id){
        return menuRepository.findById(id).orElseThrow(() -> new NotExistException("없는 메뉴 입니다."));
    }
}

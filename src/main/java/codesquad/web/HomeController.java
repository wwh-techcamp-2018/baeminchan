package codesquad.web;

import codesquad.domain.MenuContext;
import codesquad.domain.Promotion;
import codesquad.repository.MenuRepository;
import codesquad.repository.PromotionRepository;
import codesquad.support.NotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    MenuRepository menuRepository;

    @GetMapping
    public String welcomePage(Model model){
        model.addAttribute("promotions", promotionRepository.findAll());
        model.addAttribute("bestMenu", menuRepository.findById(MenuContext.BEST_CATEGORY.getId())
                .orElseThrow(() -> new NotExistException("없는 메뉴 입니다.")));
        return "/index";
    }
}

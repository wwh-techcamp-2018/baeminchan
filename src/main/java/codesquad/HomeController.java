package codesquad;

import codesquad.promotion.domain.PromotionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("promotions", promotionRepository.findAll());
        log.info("promotions : {}", promotionRepository.findAll());
        return "/index";

    }
}

package codesquad.home.web;

import codesquad.promotion.domain.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("promotions", promotionRepository.findAll());
        return "/index";
    }
}

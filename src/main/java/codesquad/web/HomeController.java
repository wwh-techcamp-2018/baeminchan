package codesquad.web;

import codesquad.domain.Promotion;
import codesquad.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    PromotionRepository promotionRepository;

    @GetMapping
    public String welcomePage(Model model){
        model.addAttribute("promotions", promotionRepository.findAll());
        return "/index";
    }
}

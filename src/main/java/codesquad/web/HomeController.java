package codesquad.web;

import codesquad.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private PromotionService promotionService;

    @GetMapping("")
    public String home(HttpSession session, Model model) {
        model.addAttribute("promotions", promotionService.getShowList());
        return "/index";
    }
}

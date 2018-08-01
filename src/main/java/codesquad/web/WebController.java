package codesquad.web;

import codesquad.promotion.service.PromotionService;
import codesquad.support.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (SessionUtil.isLoginUser(session)) {
            return "/index";
        }

        return "/login";
    }

    @GetMapping("/signup")
    public String signup(HttpSession session) {
        if (SessionUtil.isLoginUser(session)) {
            return "/index";
        }

        return "/join";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("promotions", promotionService.findAll());
        return "/index";
    }

}

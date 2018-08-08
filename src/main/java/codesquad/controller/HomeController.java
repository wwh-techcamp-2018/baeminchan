package codesquad.controller;

import codesquad.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    PromotionService promotionService;

    @ApiIgnore
    @GetMapping
    public String home(Model model) {
        model.addAttribute("promotions", promotionService.getPromotions());
        return "index";
    }
}

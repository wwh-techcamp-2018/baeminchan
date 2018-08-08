package codesquad.web.admin;

import codesquad.dto.PromotionDto;
import codesquad.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static codesquad.security.HttpSessionUtils.getUserFromSession;

@Controller
@RequestMapping("/admin/promotions")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @PostMapping("")
    public String create(PromotionDto promotionDto, HttpSession session) {
        promotionService.create(promotionDto, getUserFromSession(session));
        return "/";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, PromotionDto promotionDto, HttpSession session) {
        promotionService.update(id, promotionDto, getUserFromSession(session));
        return "/";
    }

    @DeleteMapping("/{id}")
    public String update(@PathVariable Long id, HttpSession session) {
        promotionService.delete(id, getUserFromSession(session));
        return "/";
    }
}
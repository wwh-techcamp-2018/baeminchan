package codesquad.web;

import codesquad.dto.PromotionDto;
import codesquad.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/promotion")
public class ApiPromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public List<PromotionDto> show() {
        return promotionService.findAll();
    }
}

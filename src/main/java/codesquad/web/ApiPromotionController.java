package codesquad.web;

import codesquad.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/promotion")
public class ApiPromotionController {

    @Autowired
    private PromotionService promotionService;

    @GetMapping
    public ResponseEntity show() {
        return ResponseEntity.ok().body(promotionService.findAll());
    }
}

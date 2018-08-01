package codesquad.controller;

import codesquad.domain.Promotion;
import codesquad.dto.PromotionDto;
import codesquad.security.HttpSessionUtils;
import codesquad.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/promotions")
public class ApiPromotionController {
    private static final Logger log = LoggerFactory.getLogger(ApiPromotionController.class);

    @Autowired
    private PromotionService promotionService;

    @GetMapping("")
    public Iterable<Promotion> list() {
        return promotionService.gets();
    }

    @PostMapping(value = "", consumes = { "multipart/form-data" })
    public ResponseEntity<Void> create(HttpSession httpSession, PromotionDto promotionDto) {
        log.debug("ApiPromotionController create : {}", promotionDto);
        promotionService.create(HttpSessionUtils.getUserFromSession(httpSession), promotionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        promotionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promotion> update(@PathVariable Long id, @RequestBody PromotionDto updatedPromotionDto) {
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.update(id, updatedPromotionDto));
    }


}

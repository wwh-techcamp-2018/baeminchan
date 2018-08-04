package codesquad.controller;

import codesquad.domain.Promotion;
import codesquad.dto.PromotionDto;
import codesquad.security.HttpSessionUtils;
import codesquad.service.PromotionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/promotions")
public class ApiPromotionController {
    @Autowired
    private PromotionService promotionService;

    @PostMapping("")
    public ResponseEntity<Void> create(HttpSession httpSession, PromotionDto promotionDto) {
        log.info("httpSession : {}", httpSession.getAttribute(HttpSessionUtils.SESSIONED_USER));
        promotionService.create(HttpSessionUtils.getUserFromSession(httpSession), promotionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public Iterable<Promotion> list() {
        return promotionService.gets();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promotion> update(@PathVariable Long id, @RequestBody PromotionDto updatedPromotionDto) {
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.update(id, updatedPromotionDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        promotionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

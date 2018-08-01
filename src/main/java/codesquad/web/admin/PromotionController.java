package codesquad.web.admin;

import codesquad.domain.Promotion;
import codesquad.dto.PromotionDto;
import codesquad.service.PromotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static codesquad.security.HttpSessionUtils.getUserFromSession;

@Controller
@RequestMapping("/admin/promotions")
public class PromotionController {
    private static final Logger log = LoggerFactory.getLogger(PromotionController.class);
    @Autowired
    private PromotionService promotionService;

    @PostMapping("")
    public String create(PromotionDto promotionDto, HttpSession session) {
        promotionService.create(promotionDto, getUserFromSession(session));
        return "/";
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CategoryDto categoryDto, HttpSession session) {
//        Category updatedCategory = categoryService.update(id, categoryDto, getUserFromSession(session));
//        return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id, HttpSession session) {
//        categoryService.delete(id, getUserFromSession(session));
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
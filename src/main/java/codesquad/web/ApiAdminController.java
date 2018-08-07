package codesquad.web;

import codesquad.dto.CategoryPostDto;
import codesquad.dto.PromotionDto;
import codesquad.service.CategoryService;
import codesquad.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {

    @Resource
    private CategoryService categoryService;
    @Autowired
    private PromotionService promotionService;

    @PostMapping("/categories")
    public ResponseEntity add(@RequestBody CategoryPostDto categoryPostDto) {
            categoryService.add(categoryPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/categories")
    public ResponseEntity delete(@RequestBody CategoryPostDto categoryPostDto) {
        categoryService.delete(categoryPostDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/promotions")
    public ResponseEntity addPromotion(@RequestBody PromotionDto promotionDto) {
        promotionService.save(promotionDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/promotions/{id}")
    public ResponseEntity deletePromotion(@PathVariable Long id) {
        promotionService.delete(id);
        return ResponseEntity.ok().build();
    }
}

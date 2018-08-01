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

    @PostMapping("/category")
    public ResponseEntity<Void> add(@RequestBody CategoryPostDto categoryPostDto) {
        categoryService.add(categoryPostDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/category")
    public ResponseEntity<Void> delete(@RequestBody CategoryPostDto categoryPostDto) {
        categoryService.delete(categoryPostDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/promotion")
    public ResponseEntity<Void> addPromotion(@RequestBody PromotionDto promotionDto) {
        promotionService.save(promotionDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/promotion/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id) {
        promotionService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

package codesquad.web;

import codesquad.service.BestCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/best-categories")
public class ApiBestCategoryController {
    @Autowired
    private BestCategoryService bestCategoryService;
    @GetMapping
    public ResponseEntity showAll() {
        return ResponseEntity.ok().body(bestCategoryService.findAll());
    }
}

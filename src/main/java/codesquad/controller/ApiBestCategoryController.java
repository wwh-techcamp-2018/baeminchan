package codesquad.controller;

import codesquad.domain.BestCategory;
import codesquad.domain.SideDish;
import codesquad.service.BestCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bestCategories")
@EnableCaching
public class ApiBestCategoryController {
    @Autowired
    private BestCategoryService bestCategoryService;

    @GetMapping("")
    public ResponseEntity<Iterable<BestCategory>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(bestCategoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<SideDish>> getBestSideDishes(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bestCategoryService.getBestSideDishes(id));
    }
}

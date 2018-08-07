package codesquad.bestcategory.web;

import codesquad.bestcategory.domain.BestCategory;
import codesquad.bestcategory.domain.BestCategoryRepository;
import codesquad.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories/event")
public class ApiBestCategoryController {

    @Autowired
    private BestCategoryRepository bestCategoryRepository;

    @GetMapping
    @Cacheable(value = "bestCategory")
    public ResponseEntity<RestResponse> list() {
        List<BestCategory> bestCategoryList = bestCategoryRepository.findAll();
        return ResponseEntity.ok(RestResponse.of(bestCategoryList));
    }

    @GetMapping("/{id}/banchans")
    @Cacheable(value = "bestCategoryBanchan")
    public ResponseEntity<RestResponse> listById(@PathVariable Long id) {
        BestCategory bestCategory = bestCategoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(RestResponse.of(bestCategory.getBanchans()));
    }

}

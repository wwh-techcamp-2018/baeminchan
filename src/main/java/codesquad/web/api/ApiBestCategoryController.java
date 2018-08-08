package codesquad.web.api;

import codesquad.domain.BestCategory;
import codesquad.service.BestCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/best/categories")
public class ApiBestCategoryController {
    @Autowired
    private BestCategoryService bestCategoryService;

    @GetMapping
    public Iterable<BestCategory> list() {
        return bestCategoryService.getList();
    }
}
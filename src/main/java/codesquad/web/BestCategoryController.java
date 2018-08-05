package codesquad.web;
import codesquad.domain.BestCategory;
import codesquad.service.BestCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/best-categories")
public class BestCategoryController {

    @Autowired
    private BestCategoryService bestCategoryService;

    @GetMapping
    public List<BestCategory> show () {
        return bestCategoryService.findAll();
    }
}

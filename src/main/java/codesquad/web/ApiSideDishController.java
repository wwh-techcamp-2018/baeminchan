package codesquad.web;

import codesquad.domain.SideDish;
import codesquad.service.SideDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/sidedishes")
public class ApiSideDishController {
    @Autowired
    private SideDishService sideDishService;

    @GetMapping
    public List<SideDish> search(@RequestParam("query") String searchText) {
        return sideDishService.getSearchResults(searchText);
    }
}

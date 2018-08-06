package codesquad.web;

import codesquad.domain.Product;
import codesquad.domain.TabCategory;
import codesquad.service.TabCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ApiTabUiController {

    @Autowired
    TabCategoryService tabCategoryService;

    @GetMapping("/best")
    public ResponseEntity<List<TabCategory>> getBestProducts(){
        return new ResponseEntity<List<TabCategory>>(tabCategoryService.findBestProducts(), HttpStatus.OK);
    }


}

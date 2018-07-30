package codesquad.web;

import codesquad.dto.CustomResponse;
import codesquad.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    public static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity getParentCategories() {
        CustomResponse customResponse = new CustomResponse(200, "카테고리 정보 수신 성공", categoryService.findByParentIsNull());
        return ResponseEntity.ok().body(customResponse);
    }

}

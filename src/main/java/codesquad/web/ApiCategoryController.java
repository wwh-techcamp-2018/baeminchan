package codesquad.web;

import codesquad.domain.Category;
import codesquad.dto.CategoryDTO;
import codesquad.service.CategoryService;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/categories")
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;


    @ApiOperation(value = "모든 카테고리 조회", notes = "카테고리의 id,이름,자식노드들을 제공하여 카테고리바를 생성하도록 제공")
    @GetMapping("")
    public ResponseEntity<CategoryDTO> dtoList() {
        return new ResponseEntity<>(categoryService.findRootDto(), HttpStatus.OK);
    }
}

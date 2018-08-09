package codesquad.web;

import codesquad.domain.Category;
import codesquad.dto.CategoryDTO;
import codesquad.dto.UpdateCategoryDTO;
import codesquad.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "카테고리 추가", notes = "새로운 카테고리를 추가합니다.")
    @PostMapping("/category")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.addChild(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @ApiOperation(value = "카테고리 삭제", notes = "카테고리id를 이용하여 삭제합니다.")
    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Category category = categoryService.deleteById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @ApiOperation(value = "카테고리 수정", notes = "카테고리id를 이용하여 수정합니다.")
    @PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory
            (@Valid @RequestBody UpdateCategoryDTO updateCategoryDTO, @PathVariable Long id) {
        Category category = categoryService.update(id, updateCategoryDTO);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @ApiOperation(value = "모든 카테고리와 카테고리 하위 전체 제품 조회", notes = "관리자 페이지에서 모든 카테고리별 물품을 트리로 관리할 수 있도록 제공해줍니다.")
    @GetMapping("/category")
    public ResponseEntity<Category> list() {
        return new ResponseEntity<>(categoryService.findRoot(), HttpStatus.OK);
    }
}

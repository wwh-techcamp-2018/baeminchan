package codesquad.controller;

import codesquad.domain.category.Category;
import codesquad.service.CategoryService;
import codesquad.util.CustomResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {

    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "카테고리 조회", notes = "카테고리의 전체 데이터를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @GetMapping()
    public CustomResponse<List<Category>> getCategoryList() {
        return new CustomResponse<List<Category>>(CustomResponse.MSG.OK, categoryService.getCategoryList());
    }
}

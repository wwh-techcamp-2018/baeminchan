package codesquad.controller;


import codesquad.domain.category.Category;
import codesquad.domain.product.Product;
import codesquad.dto.category.CategoryDto;
import codesquad.dto.product.ProductDto;
import codesquad.dto.user.UserSessionDto;
import codesquad.security.AdminSession;
import codesquad.service.CategoryService;
import codesquad.service.ProductService;
import codesquad.util.CustomResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "카테고리 생성", notes = "카테고리의 정보를 받아 카테고리를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "삽입 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Category> createCategory(@AdminSession UserSessionDto adminUserSessionDto,
                                                   @RequestBody CategoryDto categoryDto) {
        return new CustomResponse<Category>(CustomResponse.MSG.OK, categoryService.add(categoryDto));
    }

    @ApiOperation(value = "카테고리 삭제", notes = "카테고리의 id를 받아 카테고리를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "삭제 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @DeleteMapping("/category/{cid}")
    public CustomResponse<Category> deleteCategory(@AdminSession UserSessionDto adminUserSessionDto,
                                                   @PathVariable long cid) {
        categoryService.delete(cid);
        return new CustomResponse<Category>(CustomResponse.MSG.OK, null);
    }

    @ApiOperation(value = "카테고리 수정", notes = "카테고리의 dto를 받아 카테고리를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "수정 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PutMapping("/category")
    public CustomResponse<Category> updateCategory(@AdminSession UserSessionDto adminUserSessionDto,
                                                   @RequestBody CategoryDto categoryDto) {
        return new CustomResponse<Category>(CustomResponse.MSG.OK, categoryService.update(categoryDto));
    }

    @ApiOperation(value = "반찬 생성", notes = "반찬의 dto를 받아 반찬을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "생성 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Product> createProduct(@AdminSession UserSessionDto adminUserSessionDto,
                                                 @RequestBody ProductDto productDto) {
        return new CustomResponse<>(CustomResponse.MSG.OK, productService.add(productDto));
    }

    @ApiOperation(value = "반찬 삭제", notes = "반찬의 id를 받아 반찬을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "삭제 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @DeleteMapping("/product/{id}")
    public CustomResponse<Product> deleteProduct(@AdminSession UserSessionDto adminUserSessionDto,
                                                   @PathVariable long id) {
        productService.delete(id);
        return new CustomResponse<>(CustomResponse.MSG.OK, null);
    }

    @ApiOperation(value = "반찬 수정", notes = "반찬의 dto를 받아 반찬을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "수정 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @PutMapping("/product")
    public CustomResponse<Product> updateProduct(@AdminSession UserSessionDto adminUserSessionDto,
                                                   @RequestBody ProductDto productDto) {
        return new CustomResponse<>(CustomResponse.MSG.OK, productService.update(productDto));
    }
}

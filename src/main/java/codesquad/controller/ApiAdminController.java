package codesquad.controller;


import codesquad.domain.category.Category;
import codesquad.dto.category.CategoryDto;
import codesquad.dto.user.UserSessionDto;
import codesquad.security.AdminSession;
import codesquad.service.CategoryService;
import codesquad.util.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomResponse<Category> createCategory(@AdminSession UserSessionDto adminUserSessionDto,
                                                   @RequestBody CategoryDto categoryDto) {
        return new CustomResponse<Category>(CustomResponse.MSG.OK, categoryService.add(categoryDto));
    }

    @DeleteMapping("/category/{cid}")
    public CustomResponse<Category> deleteCategory(@AdminSession UserSessionDto adminUserSessionDto,
                                                   @PathVariable long cid) {
        categoryService.delete(cid);
        return new CustomResponse<Category>(CustomResponse.MSG.OK, null);
    }

    @PutMapping("/category")
    public CustomResponse<Category> updateCategory(@AdminSession UserSessionDto adminUserSessionDto,
                                                   @RequestBody CategoryDto categoryDto) {
        return new CustomResponse<Category>(CustomResponse.MSG.OK, categoryService.update(categoryDto));
    }
}

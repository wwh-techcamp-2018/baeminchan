package codesquad.product.web;

import codesquad.RestResponse;
import codesquad.product.domain.Category;
import codesquad.product.dto.CategoryDto;
import codesquad.product.service.CategoryService;
import codesquad.user.auth.AdminUser;
import codesquad.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/categories")
public class ApiAdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<RestResponse<Category>> create(@AdminUser User user, @RequestBody CategoryDto dto) {
        Category category = categoryService.create(dto);
        return ResponseEntity
                .created(URI.create("/api/categories/" + category.getId()))
                .body(RestResponse.success(category));
    }

    @DeleteMapping("/{id}")
    public RestResponse<?> delete(@AdminUser User user, @PathVariable Long id) {
        categoryService.delete(id);
        return RestResponse.success();
    }
}

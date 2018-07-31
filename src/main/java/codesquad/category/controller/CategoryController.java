package codesquad.category.controller;


import codesquad.category.domain.Category;
import codesquad.category.dto.CategoryDto;
import codesquad.category.service.CategoryService;
import codesquad.security.LoginUser;
import codesquad.support.dto.response.ResponseModel;
import codesquad.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<List<Category>> topCategories() {
        return ResponseModel.ofSuccess(categoryService.getTopCategories(), null);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<List<Category>> subCategories(@PathVariable Long id) {
        return ResponseModel.ofSuccess(categoryService.getSubCategories(id), null);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseModel<Category> create(@LoginUser(adminRequired = true) User user, @Valid @RequestBody CategoryDto dto) {
        Category saved = categoryService.create(user, dto);
        return ResponseModel.ofSuccess(saved, "/api/category/" + saved.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseModel<Category> delete(@LoginUser(adminRequired = true) User user, @PathVariable Long id) {
        return ResponseModel.ofSuccess(categoryService.delete(user, id), null);
    }


}

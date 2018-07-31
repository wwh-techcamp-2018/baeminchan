package codesquad.category.web;

import codesquad.category.domain.Category;
import codesquad.category.domain.CategoryRepository;
import codesquad.category.dto.CategoryDto;
import codesquad.user.security.LoginUser;
import codesquad.utils.RestResponse;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class ApiCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public ResponseEntity<RestResponse> list() {
        return listById(Category.ROOT);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<RestResponse> listById(@PathVariable Long id) {
        Category sub = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        log.info("sub: {}", sub);
        List<Category> categoryList = sub.getChildren();
        log.info("category List: {}", categoryList);
        return ResponseEntity.ok().body(RestResponse.builder().data(categoryList).build());
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<Void> create(@RequestBody @Valid CategoryDto categoryDto) {
        //@LoginUser(role = Role.ADMIN) User user,
        log.info("user : {}", categoryDto);
        return createSubCategory(0L, categoryDto);
    }

    @PostMapping("/admin/categories/{id}")
    public ResponseEntity<Void> createSubCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto) {
        Category parent = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Category newCategory = Category.builder()
                .parent(parent)
                .name(categoryDto.getName())
                .build();

        Category savedCategory = categoryRepository.save(newCategory);

        return ResponseEntity.created(URI.create("/admin/categories/" + savedCategory.getId())).build();
    }

    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        category.update(categoryDto);
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        category.delete();

        log.info("category deleted : {}",categoryRepository.save(category));

        return ResponseEntity.ok().build();
    }
}

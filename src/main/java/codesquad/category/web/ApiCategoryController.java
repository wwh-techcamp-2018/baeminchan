package codesquad.category.web;

import codesquad.RestResponse;
import codesquad.category.domain.*;
import codesquad.security.HttpSessionUtils;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class ApiCategoryController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BestCategoryRepository bestCategoryRepository;

    @ApiOperation(value = "카테고리 전체 목록 조회")
    @GetMapping("/category")
    public ResponseEntity<Category> list() {
        Category category = findById(Category.ROOT_ID);

        return ResponseEntity.ok(category);
    }

    @ApiOperation(value = "(이벤트) 베스트 반찬 카테고리 목록 조회")
    @GetMapping("/category/best")
    @Cacheable(value="bestCategory")
    public ResponseEntity<RestResponse> getBestCategory() {
        List<BestCategory> bestCategories = bestCategoryRepository.findAll();
        return ResponseEntity.ok(RestResponse.of(bestCategories));
    }

    @PostMapping("/admin/category")
    public ResponseEntity<Void> create(@Valid @RequestBody CategoryDto categoryDto) {
        return create(Category.ROOT_ID, categoryDto);
    }

    @PostMapping("/admin/category/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        Category newCategory = Category.of(categoryDto.getName(), findById(id));
        Category savedCategory = categoryRepository.save(newCategory);
        return ResponseEntity.created(URI.create("/category/" + savedCategory.getId())).build();
    }

    @PutMapping("/admin/category/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto, HttpSession session) {
        Category category = findById(id);

        Long parentId = Optional.ofNullable(categoryDto.getParentId()).orElse(category.getParent().getId());
        Category parent = findById(parentId);

        Long userId = HttpSessionUtils.getUserSession(session);
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        category.update(user, categoryDto.getName(), parent);
        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/admin/category/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }

    private Category findById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}

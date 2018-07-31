package codesquad.category.web;

import codesquad.category.domain.Category;
import codesquad.category.domain.CategoryDto;
import codesquad.category.domain.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@Slf4j
public class ApiCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<Category> list() {
        Category category = findById(Category.ROOT_ID);

        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CategoryDto categoryDto) {
        return create(Category.ROOT_ID, categoryDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        Category newCategory = Category.builder()
                .parent(findById(id))
                .name(categoryDto.getName())
                .build();
        Category savedCategory = categoryRepository.save(newCategory);
        return ResponseEntity.created(URI.create("/category/" + savedCategory.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        Category category = findById(id);

        Long parentId = Optional.ofNullable(categoryDto.getParentId()).orElse(category.getParent().getId());
        Category parent = findById(parentId);

        category.update(categoryDto.getName(), parent);
        categoryRepository.save(category);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
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

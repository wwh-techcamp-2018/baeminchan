package codesquad.web;

import codesquad.domain.Category;
import codesquad.domain.CategoryDTO;
import codesquad.repository.CategoryRepository;
import codesquad.repository.UserRepository;
import codesquad.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BannerController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/categories/root")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> rootCategories() {
        return categoryService.findCategoriesByParent(null);
    }

    @GetMapping("/api/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO fetchCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryDTOById(id);
    }
}

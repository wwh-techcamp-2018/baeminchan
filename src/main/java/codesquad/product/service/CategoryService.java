package codesquad.product.service;

import codesquad.product.domain.Category;
import codesquad.product.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getMainCategories() {
        return categoryRepository.findByParentCategoryId(null);
    }

    public List<Category> getSubCategories(Long id) {
        Category mainCategory = categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return categoryRepository.findByParentCategoryId(mainCategory.getId());
    }
}

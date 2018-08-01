package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.domain.DomainField;
import codesquad.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category saveWithParent(Long parentId, Category category) {
        Category parentCategory = findExistCategory(parentId);
        category.setParent(parentCategory);
        return categoryRepository.save(category);
    }

    @Cacheable(value = "rootCategories")
    public List<Category> getRootCategories() {
        return categoryRepository.findByParentNull();
    }

    @Cacheable(value = "category", key = "#id")
    public Category category(Long id) {
        return findExistCategory(id);
    }

    public Category update(Long id, Category updateCategory) {
        Category originalCategory = findExistCategory(id);
        originalCategory.update(updateCategory);
        return categoryRepository.save(originalCategory);
    }

    public void delete(Long id) {
        categoryRepository.delete(findExistCategory(id));
    }

    private Category findExistCategory(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(DomainField.ID, "존재하지 않는 카테고리입니다."));
    }
}

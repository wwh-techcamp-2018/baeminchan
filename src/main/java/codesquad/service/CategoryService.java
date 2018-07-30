package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.domain.DomainField;
import codesquad.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void saveWithParent(Long parentId, Category category) {
        Category parentCategory = findExistCategory(parentId);
        category.setParent(parentCategory);
        categoryRepository.save(category);
    }

    public void update(Long id, Category updateCategory) {
        Category originalCategory = findExistCategory(id);
        originalCategory.update(updateCategory);
        categoryRepository.save(originalCategory);
    }

    private Category findExistCategory(Long id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(() -> new BadRequestException(DomainField.ID, "존재하지 않는 카테고리입니다."));
    }
}

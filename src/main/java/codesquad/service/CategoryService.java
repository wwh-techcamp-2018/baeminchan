package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryDTO;
import codesquad.exception.NotFoundException;
import codesquad.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("categoryService")
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void deleteById(Long id) {
        delete(findCategoryById(id));
    }

    public Category update(Long id, Category updateCategory) {
        Category originalCategory = findCategoryById(id);
        originalCategory.update(updateCategory);
        return categoryRepository.save(originalCategory);
    }

    public List<Category> findCategoriesByParent(Category parent) {
        return categoryRepository.findCategoriesByParentEquals(parent);
    }

    public Category findCategoryById(Long id) {
        Optional<Category> maybeCategory = categoryRepository.findCategoryById(id);
        if(!maybeCategory.isPresent()) {
            throw new NotFoundException();
        }
        return maybeCategory.get();

    }

    public CategoryDTO findCategoryDTOById(Long id) {
        return new CategoryDTO(findCategoryById(id), findCategoriesByParent(findCategoryById(id)));
    }

}

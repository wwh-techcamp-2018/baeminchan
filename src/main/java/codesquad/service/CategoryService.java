package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryDTO;
import codesquad.domain.Product;
import codesquad.exception.NotFoundException;
import codesquad.repository.CategoryRepository;
import codesquad.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("categoryService")
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    private void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void deleteById(Long id) {
        delete(findCategoryById(id));
    }

    @CacheEvict(value="findCategoryCache", key = "#id")
    public Category update(Long id, Category updateCategory) {
        Category originalCategory = findCategoryById(id);
        originalCategory.update(updateCategory);
        return categoryRepository.save(originalCategory);
    }

    public List<Category> findCategoriesByParent(Category parent) {
        return categoryRepository.findCategoriesByParentEquals(parent);
    }

    @Cacheable(value = "findCategoryCache", key = "#id")
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

    @Cacheable(value = "findProductsByCategoryId", key = "#id")
    public List<Product> findProductsByCategoryId(Long id) {
        return productRepository.findAllByCategoryEquals(
                categoryRepository.findById(id).orElseThrow(NotFoundException::new)
        );
    }

}

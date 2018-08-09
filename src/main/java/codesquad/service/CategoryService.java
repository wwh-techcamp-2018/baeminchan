package codesquad.service;

import codesquad.domain.Category;
import codesquad.dto.CategoryDTO;
import codesquad.dto.UpdateCategoryDTO;
import codesquad.exception.CategoryNotFoundException;
import codesquad.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    public static final Long ROOT_ID = 0l;

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findRoot() {
        return findById(ROOT_ID);
    }

    public CategoryDTO findRootDto() {return findById(ROOT_ID).toDTO();}

    private Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    public List<Category> findAll(){
        return categoryRepository.findByParent(null);
    }

    public Category addChild(CategoryDTO categoryDTO) {
        Category parentCategory = findById(categoryDTO.getParentId());
        Category childCategory = parentCategory.addChild(categoryDTO);
        return categoryRepository.save(childCategory);
    }

    public Category deleteById(Long id) {
        Category category = findById(id);
        category.delete();
        return categoryRepository.save(category);
    }

    public Category update(Long id, UpdateCategoryDTO updateCategoryDTO) {
        Category category = findById(id);
        category.update(updateCategoryDTO);
        return categoryRepository.save(category);
    }
}

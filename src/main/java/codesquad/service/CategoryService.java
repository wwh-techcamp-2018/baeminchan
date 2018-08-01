package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category create(Long parentId, CategoryDto categoryDto, User loginUser) {
        if (parentId == null)
            return categoryRepository.save(categoryDto.toCategory(null));

        return categoryRepository.save(categoryDto.toCategory(categoryRepository.findById(parentId).get()));
    }

    public List<Category> getList(Long parentId) {
        return categoryRepository.findByParentCategoryId(parentId);
    }

    @Transactional
    public Category update(Long id, CategoryDto categoryDto, User loginUser) {
        Category savedCategory = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return savedCategory.update(categoryDto);
    }

    @Transactional
    public void delete(Long id, User loginUser) {
        Category savedCategory = categoryRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        savedCategory.delete();
    }
}

package codesquad.service;

import codesquad.domain.category.Category;
import codesquad.domain.category.CategoryRepository;
import codesquad.dto.category.CategoryDto;
import codesquad.exception.CategoryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category add(CategoryDto categoryDto) {
        if (categoryDto.isRoot()) {
            return categoryRepository.save(new Category(categoryDto.getTitle()));
        }

        Category parent = categoryRepository
                .findById(categoryDto.getParentId())
                .orElseThrow(CategoryNotFoundException::new);
        parent.addChild(new Category(categoryDto.getTitle()));
        return categoryRepository.save(parent);
    }

    @Cacheable(value = "categories")
    public List<Category> getCategoryList() {
        return categoryRepository.findByParent(null);
    }

    public void delete(Long cid) {
        categoryRepository.deleteById(cid);
    }

    @Transactional
    public Category update(CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryDto.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Category parent = null;

        if (!categoryDto.isRoot()) {
            parent = categoryRepository.findById(categoryDto.getParentId()).orElseThrow(CategoryNotFoundException::new);
        }

        category.update(categoryDto, parent);
        return category;
    }
}

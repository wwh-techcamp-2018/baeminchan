package codesquad.service;

import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import codesquad.repository.CategoryRepository;
import codesquad.support.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin.cache.OldCacheEntry;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Service
public class CategoryService {

    @Resource(name = "categoryRepository")
    CategoryRepository categoryRepository;

    private static final Long ROOT = 0L;

    public List<Category> getCategories(){
        return categoryRepository.findByParentId(ROOT).orElse(new ArrayList<>());
    }

    public Category create(CategoryDto categoryDto, Long parentId) {
        parentId = (parentId == null) ? ROOT : parentId;
        Category parentCategory = categoryRepository.findById(parentId).orElseThrow(() -> new NotExistException("존재하지 않은 카테고리입니다."));
        return categoryRepository.save(categoryDto.toCategory(parentCategory));
    }

    public Category delete(Long id) {
        Category category = categoryRepository.findById(id).filter(x -> x.isNotRootCategory(ROOT)).orElseThrow(() -> new NotExistException("존재하지 않은 카테고리입니다."));
        categoryRepository.delete(category);
        return category;
    }

}

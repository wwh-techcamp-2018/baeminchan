package codesquad.service;

import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import codesquad.repository.CategoryRepository;
import codesquad.support.NotExistException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Resource(name = "categoryRepository")
    CategoryRepository categoryRepository;

    private static final Long ROOT = 0L;

    public List<Category> getCategories(){
        return categoryRepository.findByParentId(ROOT).orElse(new ArrayList<>());
    }

    public Category create(CategoryDto categoryDto, Long parentId) {
        Long newParentId = (parentId == null) ? ROOT : parentId;
        Category parentCategory = categoryRepository.findById(newParentId).orElseThrow(() -> new NotExistException("존재하지 않은 카테고리입니다."));
        return categoryRepository.save(categoryDto.toCategory(parentCategory));
    }

    public Category delete(Long id) {
        Category category = categoryRepository.findById(id).filter(x -> x.isNotSameId(ROOT)).orElseThrow(() -> new NotExistException("존재하지 않은 카테고리입니다."));
        category.setChildren(null);
        categoryRepository.delete(category);
        return category;
    }

}

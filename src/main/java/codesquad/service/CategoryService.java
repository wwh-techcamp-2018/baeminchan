package codesquad.service;

import codesquad.domain.Category;
import codesquad.dto.CategoryDto;
import codesquad.repository.CategoryRepository;
import codesquad.support.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Resource(name = "categoryRepository")
    CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findByParentIsNull().orElse(new ArrayList<>());
    }

    public Category create(CategoryDto categoryDto, Long parentId) {
        Category parentCategory = categoryRepository.findById(parentId).orElseThrow(() -> new NotExistException("존재하지 않은 카테고리입니다."));
        return categoryRepository.save(categoryDto.toCategory(parentCategory));
    }

    public Category delete(Long parentId, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotExistException("존재하지 않은 카테고리입니다."));
        //category.setParent(null);
        categoryRepository.delete(category);
        return category;
    }

}

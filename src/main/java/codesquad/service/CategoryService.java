package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import codesquad.exception.NotMatchedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getParentList() {
        return categoryRepository.findByParentCategoryIsNullOrderByPriorityAsc();
    }

    public Category getOne(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotMatchedException("아이디에 해당하는 카테고리가 없습니다."));
    }

    public void addCategory(User user, CategoryDto categoryDto) {
        Category parent = getOne(categoryDto.getParentId());
        categoryRepository.save(categoryDto.toCategory(user, parent));
    }
}

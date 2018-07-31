package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import codesquad.exception.NotMatchedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getParentList() {
        return categoryRepository.findByDeletedAndParentCategoryIsNullOrderByPriorityAsc(false);
    }

    public Category getOne(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotMatchedException("아이디에 해당하는 카테고리가 없습니다."));
    }

    public Category addCategory(User user, CategoryDto categoryDto) {
        Category parent = (categoryDto.getParentId() == null) ? null : getOne(categoryDto.getParentId());
        return categoryRepository.save(categoryDto.toNewCategory(user, parent));
    }

    @Transactional
    public void delete(User user, Long targetId) {
        Category category = getOne(targetId);
        category.deleteAll(user);
    }

    @Transactional
    public Category update(User user, CategoryDto categoryDto, Long id) {
        Category category = getOne(id);
        category.update(categoryDto.toCategory(user, getOne(categoryDto.getParentId())));
        return category;
    }
}

package codesquad.category.service;

import codesquad.category.domain.Category;
import codesquad.category.domain.CategoryRepository;
import codesquad.category.dto.CategoryDto;
import codesquad.exception.BadRequestException;
import codesquad.exception.ForbiddenException;
import codesquad.exception.ResourceNotFoundException;
import codesquad.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(User maybeAdmin, CategoryDto dto) {
        log.debug("isAdmin : {}", maybeAdmin.isAdmin());
        checkAdmin(maybeAdmin);

        Category parent = null;
        if (dto.getParentCategoryId() != null) {
            parent = categoryRepository.findById(dto.getParentCategoryId())
                    .orElseThrow(() -> new BadRequestException("parentCategoryId", "상위 카테고리가 존재하지 않습니다."));
        }

        return categoryRepository.save(new Category(dto.getTitle(), parent));
    }

    @Transactional
    public Category delete(User maybeAdmin, Long id) {
        checkAdmin(maybeAdmin);
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id", "존재하지 않는 카테고리입니다."));
        categoryRepository.deleteByParentCategory(category);
        categoryRepository.delete(category);
        return category;
    }

    private void checkAdmin(User user) {
        if (!user.isAdmin()) {
            throw new ForbiddenException("관리자 권한이 필요합니다.");
        }

    }

    public List<Category> getTopCategories() {
        return categoryRepository.findAllByParentCategoryIsNull();
    }

    public List<Category> getSubCategories(Long id) {
        return categoryRepository.findByParentCategoryId(id);
    }
}

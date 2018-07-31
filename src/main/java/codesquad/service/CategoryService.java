package codesquad.service;

import codesquad.domain.category.Category;
import codesquad.domain.category.CategoryRepository;
import codesquad.dto.category.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category add(CategoryDto categoryDto) {
        // TODO: 2018. 7. 27. 카테고리가 없을 때 커스텀 에러를 만들어 주어야합니다.
        if (categoryDto.isRoot()) {
            return categoryRepository.save(new Category(categoryDto.getTitle()));
        }

        Category parent = categoryRepository
                .findById(categoryDto.getParentId())
                .orElseThrow(RuntimeException::new);
        parent.addChild(new Category(categoryDto.getTitle()));
        return categoryRepository.save(parent);
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findByParent(null);
    }
}

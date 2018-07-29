package codesquad.service;

import codesquad.domain.category.Category;
import codesquad.domain.category.CategoryRepository;
import codesquad.dto.category.ChildCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category addChild(ChildCategoryDto childCategoryDto) {
        // TODO: 2018. 7. 27. 카테고리가 없을 때 커스텀 에러를 만들어 주어야합니다. 
        Category parent = categoryRepository
                 .findById(childCategoryDto.getParentId())
                 .orElseThrow(RuntimeException::new);
        parent.addChild(new Category(childCategoryDto.getChildTitle()));
        return categoryRepository.save(parent);
    }
}

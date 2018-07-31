package codesquad.service;

import codesquad.domain.category.Category;
import codesquad.domain.category.CategoryRepository;
import codesquad.dto.category.CategoryDto;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
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

    public void delete(Long cid) {
        // TODO: 2018. 7. 31. 없을 때 커스텀 에러
        categoryRepository.deleteById(cid);
    }

    @Transactional
    public Category update(CategoryDto categoryDto) {
        // TODO: 2018. 7. 31. Custom Error
        Category category = categoryRepository.findById(categoryDto.getCategoryId())
                .orElseThrow(RuntimeException::new);
        Category parent = null;

        if(!categoryDto.isRoot()) {
            // TODO: 2018. 7. 31. Custom Error 
            parent = categoryRepository.findById(categoryDto.getParentId()).orElseThrow(RuntimeException::new);
        }

        category.update(categoryDto, parent);
        return category;
    }
}

package codesquad.service;

import codesquad.domain.Category;
import codesquad.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getParentList() {
        return categoryRepository.findByParentCategoryIsNull();
    }

    public Category getOne() {
        return categoryRepository.findById(1L).get();
    }
}

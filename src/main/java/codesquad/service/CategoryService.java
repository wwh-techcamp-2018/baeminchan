package codesquad.service;

import codesquad.domain.Category;
import codesquad.exception.CategoryNotFoundException;
import codesquad.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    public static final Long ROOT_ID = 0l;

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findRoot() {
        return findById(ROOT_ID);
    }

    private Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
}

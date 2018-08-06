package codesquad.service;

import codesquad.domain.Category;
import codesquad.dto.CategoryListDto;
import codesquad.dto.CategoryPostDto;
import codesquad.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service("categoryService")
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);


    @Resource
    private CategoryRepository categoryRepository;

    public CategoryListDto getParents() {

        CategoryListDto test = new CategoryListDto(categoryRepository.findByParentIsNull()
                .stream()
                .map((parent) -> Category.toDto(parent))
                .collect(Collectors.toList()));
        log.debug(test.toString());
        return test;
    }

    @Transactional
    public Category add(CategoryPostDto categoryPostDto) {
        Category parent = categoryRepository.findByNameAndParentIsNull(categoryPostDto.getParent()).get();
        Category child = new Category(categoryPostDto.getName(), null, new ArrayList<Category>());
        parent.addChild(child);
        return categoryRepository.save(child);
    }

    @Transactional
    public void delete(CategoryPostDto categoryPostDto) {
        Category child = categoryRepository.findByName(categoryPostDto.getName())
                .stream()
                .filter(c -> c.getParent().getName().equals(categoryPostDto.getParent()))
                .findFirst()
                .get();
        child.removeReference();
        categoryRepository.delete(child);
    }
}

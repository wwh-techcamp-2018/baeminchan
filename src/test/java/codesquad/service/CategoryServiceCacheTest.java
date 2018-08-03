package codesquad.service;

import codesquad.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryServiceCacheTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void cacheData() {
        Category category = categoryService.findCategoryById(1L);
        Category updated = new Category("updated title", category.getParent());
        categoryService.update(category.getId(), updated);
        Category maybeCached = categoryService.findCategoryById(category.getId());

        assertThat(maybeCached).isEqualTo(category);
    }
}

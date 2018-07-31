package codesquad.product.web;


import codesquad.RestResponse;
import codesquad.product.domain.Category;
import codesquad.product.domain.CategoryRepository;
import codesquad.support.ApiAcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiCategoryAcceptanceTest extends ApiAcceptanceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private List<Category> categories;

    @Before
    public void setUp() throws Exception {
        categoryRepository.deleteAll();

        categories = Arrays.asList(
                Category.builder().title("Main 1").build(),
                Category.builder().title("Main 2").build()
        );

        categoryRepository.saveAll(categories);

        for (Category main : categories) {
            for (int i = 0; i < 2; i++) {
                Category child = categoryRepository.save(Category.builder().title("Child " + i).parentCategory(main).build());
                main.addChildCategory(child);
            }
        }

        categoryRepository.saveAll(categories);
    }

    @Test
    public void mainCategory() {
        ResponseEntity<RestResponse<List<Category>>> response = getResponseEntityList("/api/categories", getCategoryListType());
        List<Category> responseCategories = response.getBody().getData();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseCategories).isEqualTo(categories);
    }


    @Test
    public void subCategory() {
        Category mainCategory = categories.get(0);
        ResponseEntity<RestResponse<List<Category>>> response = getResponseEntityList("/api/categories/" + mainCategory.getId(), getCategoryListType());
        List<Category> responseCategories = response.getBody().getData();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseCategories).isEqualTo(mainCategory.getChildCategories());
    }

    private ParameterizedTypeReference<RestResponse<List<Category>>> getCategoryListType() {
        return new ParameterizedTypeReference<RestResponse<List<Category>>>() {
        };
    }
}

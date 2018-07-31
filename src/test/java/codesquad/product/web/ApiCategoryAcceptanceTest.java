package codesquad.product.web;


import codesquad.RestResponse;
import codesquad.product.domain.Category;
import codesquad.product.domain.CategoryRepository;
import codesquad.product.dto.CategoryDto;
import codesquad.support.ApiAcceptanceTest;
import codesquad.user.domain.Role;
import codesquad.user.domain.User;
import codesquad.user.domain.UserRepository;
import codesquad.user.domain.UserTest;
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

    @Autowired
    private UserRepository userRepository;

    private List<Category> categories;

    private Category mainCategory;

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAll();
        categoryRepository.findAllByParentCategory(null).forEach(category -> categoryRepository.delete(category));

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

        mainCategory = categories.get(0);
    }

    @Test
    public void getMainCategory() {
        ResponseEntity<RestResponse<List<Category>>> response = getResponseEntityList("/api/categories", getCategoryListType());
        List<Category> responseCategories = response.getBody().getData();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseCategories).isEqualTo(categories);
    }


    @Test
    public void getSubCategory() {
        ResponseEntity<RestResponse<List<Category>>> response = getResponseEntityList("/api/categories/" + mainCategory.getId(), getCategoryListType());
        List<Category> responseCategories = response.getBody().getData();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseCategories).isEqualTo(mainCategory.getChildCategories());
    }

    @Test
    public void createMainCategory() {
        CategoryDto dto = mainCategoryDto();

        User rawUser = makeUser(Role.ADMIN);

        ResponseEntity<RestResponse<Category>> response =
                createPostResponseEntityWithUser(
                        rawUser,
                        "/api/categories",
                        dto,
                        getCategoryType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getData().getTitle()).isEqualTo(dto.getTitle());
        assertThat(response.getBody().getData().getParentCategory()).isNull();
    }

    @Test
    public void createMainCategory_not_admin() {
        CategoryDto dto = mainCategoryDto();

        User rawUser = makeUser(Role.USER);

        ResponseEntity<RestResponse<Category>> response =
                createPostResponseEntityWithUser(
                        rawUser,
                        "/api/categories",
                        dto,
                        getCategoryType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }


    @Test
    public void createMainCategory_not_login() {
        CategoryDto dto = subCategoryDto();

        ResponseEntity<RestResponse<Category>> response =
                createPostResponseEntity("/api/categories", dto, getCategoryType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void createSubCategory() {
        CategoryDto dto = subCategoryDto();

        User rawUser = makeUser(Role.ADMIN);

        ResponseEntity<RestResponse<Category>> response = createPostResponseEntityWithUser(rawUser, "/api/categories", dto, getCategoryType());

        Category savedSubCategory = categoryRepository.findById(response.getBody().getData().getId()).get();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(savedSubCategory.getTitle()).isEqualTo(dto.getTitle());
        assertThat(savedSubCategory.getParentCategory()).isEqualTo(mainCategory);
    }

    @Test
    public void createSubCategory_not_admin() {
        User rawUser = makeUser(Role.USER);

        ResponseEntity<RestResponse<Category>> response =
                createPostResponseEntityWithUser(
                        rawUser,
                        "/api/categories",
                        subCategoryDto(),
                        getCategoryType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void createSubCategory_not_login() {
        ResponseEntity<RestResponse<Category>> response =
                createPostResponseEntity(
                        "/api/categories",
                        subCategoryDto(),
                        getCategoryType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void deleteMainCategory() {
        User rawUser = makeUser(Role.ADMIN);

        ResponseEntity<RestResponse<Category>> response =
                deleteEntityWithUser(
                        rawUser,
                        "/api/categories/" + mainCategory.getId(),
                        getCategoryType());

        Category deleted = categoryRepository.findById(mainCategory.getId()).orElse(null);
        List<Category> savedSubCategory = categoryRepository.findAllByParentCategory(mainCategory);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(deleted).isNull();
        assertThat(savedSubCategory).isEmpty();
    }

    @Test
    public void deleteMainCategory_not_admin() {
        User rawUser = makeUser(Role.USER);

        ResponseEntity<RestResponse<Category>> response =
                deleteEntityWithUser(
                        rawUser,
                        "/api/categories/" + mainCategory.getId(),
                        getCategoryType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void deleteSubCategory() {
        User rawUser = makeUser(Role.ADMIN);

        Category subCategory = mainCategory.getChildCategories().get(0);

        ResponseEntity<RestResponse<Category>> response =
                deleteEntityWithUser(
                        rawUser,
                        "/api/categories/" + subCategory.getId(),
                        getCategoryType());

        Category deleted = categoryRepository.findById(subCategory.getId()).orElse(null);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(deleted).isNull();
    }


    private ParameterizedTypeReference<RestResponse<Category>> getCategoryType() {
        return new ParameterizedTypeReference<RestResponse<Category>>() {
        };
    }

    private ParameterizedTypeReference<RestResponse<List<Category>>> getCategoryListType() {
        return new ParameterizedTypeReference<RestResponse<List<Category>>>() {
        };
    }


    private CategoryDto mainCategoryDto() {
        return CategoryDto.builder()
                .title("category")
                .build();
    }

    private CategoryDto subCategoryDto() {
        return CategoryDto.builder()
                .title("category")
                .parentCategoryId(mainCategory.getId())
                .build();
    }

    private User makeUser(Role role) {
        User rawUser = UserTest.userBuilder().password(UserTest.RAW_PASSWORD).role(role).build();
        User savedUser = UserTest.userBuilder().role(role).build();
        userRepository.save(savedUser);

        return rawUser;
    }
}

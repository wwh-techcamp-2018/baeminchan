package codesquad.web;


import codesquad.dto.CategoryDto;
import codesquad.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryInsertTest {
    @Autowired
    private CategoryService CategoryService;

    @Test
    public void initCategoryDtoTest() {
        CategoryDto parentCategoryDto1 = new CategoryDto("밑반찬");
        parentCategoryDto1.setChildren(createChildren(
                Arrays.asList(
                        "무침",
                        "나물무침",
                        "볶음",
                        "조림",
                        "김치",
                        "전",
                        "장아찌·피클",
                        "젓갈·장·소스",
                        "세트")
        ));
        CategoryDto parentCategoryDto2 = new CategoryDto("국찌개");
        parentCategoryDto2.setChildren(createChildren(
                Arrays.asList(
                        "국",
                        "찌개",
                        "탕",
                        "전골",
                        "세트"
                )
        ));
        CategoryDto parentCategoryDto3 = new CategoryDto("메인반찬");
        parentCategoryDto3.setChildren(createChildren(
                Arrays.asList(
                        "고기반찬",
                        "해산물반찬",
                        "생선반찬",
                        "덮밥",
                        "튀김",
                        "면",
                        "양식",
                        "아시아식",
                        "분식",
                        "죽",
                        "세트"
                )
        ));
        CategoryDto parentCategoryDto4 = new CategoryDto("아이반찬");
        parentCategoryDto4.setChildren(createChildren(
                Arrays.asList(
                        "이유식 초기/중기",
                        "이유식 후기/완료기",
                        "아이반찬",
                        "어린이반찬",
                        "간식·음료"
                )
        ));
        CategoryDto parentCategoryDto5 = new CategoryDto("정기식단");
        parentCategoryDto5.setChildren(createChildren(
                Arrays.asList(
                        "1~2인",
                        "3~4인",
                        "아이반찬"
                )
        ));
        CategoryDto parentCategoryDto6 = new CategoryDto("간편식");
        parentCategoryDto6.setChildren(createChildren(
                Arrays.asList(
                        "간편반찬",
                        "간편국찌개",
                        "간편식품"
                )
        ));
        CategoryDto parentCategoryDto7 = new CategoryDto("간식");
        parentCategoryDto7.setChildren(createChildren(
                Arrays.asList(
                        "베이커리",
                        "과일",
                        "주스",
                        "스무디",
                        "유제품·커피",
                        "기타간식"
                )
        ));
        CategoryDto parentCategoryDto8 = new CategoryDto("브랜드관");
        parentCategoryDto8.setChildren(createChildren(
                Arrays.asList(
                        "반찬가게",
                        "반찬장인",
                        "셰프의요리",
                        "전국맛집",
                        "키즈관"
                )
        ));

        CategoryService.save(parentCategoryDto1);
        CategoryService.save(parentCategoryDto2);
        CategoryService.save(parentCategoryDto3);
        CategoryService.save(parentCategoryDto4);
        CategoryService.save(parentCategoryDto5);
        CategoryService.save(parentCategoryDto6);
        CategoryService.save(parentCategoryDto7);
        CategoryService.save(parentCategoryDto8);
    }

    private List<CategoryDto> createChildren(List<String> children) {
        return children.stream().map((s) -> new CategoryDto(s)).collect(Collectors.toList());
    }

}

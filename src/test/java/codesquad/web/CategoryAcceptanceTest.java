package codesquad.web;

import codesquad.domain.Member;
import codesquad.dto.CategoryDto;
import codesquad.dto.CustomResponse;
import codesquad.dto.MemberDto;
import codesquad.service.CategoryService;
import codesquad.service.MemberService;
import codesquad.support.Role;
import codesquad.validation.ValidationMessageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CategoryAcceptanceTest {
    public static final Logger log = LoggerFactory.getLogger(MemberAcceptanceTest.class);
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MemberService memberService;

    @Test
    public void categoryCreateTest() {
        //회원가입 진행
        String email = "pobi@naver.com";

        ResponseEntity<Member> response = template.postForEntity("/api/members",
                MemberDto.defaultMemberDto(), Member.class);

        Member member = memberService.findByEmail(email);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(member).isNotNull();

        memberService.addRole(member, Role.ADMIN);

        String name = "메인반찬";
        CategoryDto categoryDto = CategoryDto.defaultCategoryDto().setName(name);
        categoryDto.addChild(CategoryDto.defaultCategoryDto().setName("child1"));
        categoryDto.addChild(CategoryDto.defaultCategoryDto().setName("child2"));

        ResponseEntity<CustomResponse> categoryResponse = template.withBasicAuth(email, "1234")
                .postForEntity("/admin/categories", categoryDto, CustomResponse.class);

        assertThat(categoryResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(categoryService.findByName(name)).isNotNull();
        assertThat(categoryService.findByName(name).getChildren().size()).isEqualTo(2);
    }

    @Test
    public void categoryCreateFailForNoLoginTest() {
        String name = "메인반찬";
        CategoryDto categoryDto = CategoryDto.defaultCategoryDto().setName(name);
        categoryDto.addChild(CategoryDto.defaultCategoryDto().setName("child1"));
        categoryDto.addChild(CategoryDto.defaultCategoryDto().setName("child2"));

        ResponseEntity<CustomResponse> categoryResponse = template.postForEntity("/admin/categories", categoryDto, CustomResponse.class);

        assertThat(categoryResponse.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(categoryResponse.getBody().getMessage()).isEqualTo(ValidationMessageUtil.UNAUTHENTICATION);
    }
}

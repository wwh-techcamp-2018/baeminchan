package codesquad.web;

import codesquad.domain.Category;
import codesquad.domain.Member;
import codesquad.dto.CategoryDto;
import codesquad.dto.CustomResponse;
import codesquad.dto.LoginDto;
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
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

        //로그인 진행
        ResponseEntity<String> loginResponse = template.postForEntity("/api/members/login", LoginDto.defaultLoginDto(), String.class);
        String[] cookieEntries = loginResponse.getHeaders().get("Set-Cookie").get(0).split(";");
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        String name = "메인반찬";
        CategoryDto categoryDto = CategoryDto.defaultCategoryDto().setName(name);
        categoryDto.addChild(CategoryDto.defaultCategoryDto().setName("child1"));
        categoryDto.addChild(CategoryDto.defaultCategoryDto().setName("child2"));

        //쿠키 JSESSIONID 추출 및 category 생성
        String jSessionId = null;
        for (String cookie : cookieEntries) {
            if (cookie.startsWith("JSESSIONID")) {
                jSessionId = cookie.split("=")[1];
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", "JSESSIONID=" + jSessionId);
        HttpEntity<String> requestEntity = new HttpEntity(categoryDto, headers);
        ResponseEntity<Category> categoryResponse = template.exchange("/admin/categories", HttpMethod.POST, requestEntity, Category.class);

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

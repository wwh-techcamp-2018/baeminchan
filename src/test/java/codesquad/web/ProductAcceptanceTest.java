package codesquad.web;

import codesquad.domain.Member;
import codesquad.domain.Product;
import codesquad.dto.MemberDto;
import codesquad.service.MemberService;
import codesquad.support.AcceptanceTestTemplate;
import codesquad.support.Role;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductAcceptanceTest extends AcceptanceTestTemplate {

    @Autowired
    private MemberService memberService;

    @Test
    public void show() {
        ResponseEntity<List<Product>> response =
                getForEntityWithParameterized("/api/products?searchText=라면", new ParameterizedTypeReference<List<Product>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isNotZero();
    }

    @Test
    public void create() {
        //회원가입 진행
        String email = "pobi@naver.com";

        ResponseEntity<Member> response = template.postForEntity("/api/members",
                MemberDto.defaultMemberDto(), Member.class);

        Member member = memberService.findByEmail(email);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(member).isNotNull();

        memberService.addRole(member, Role.ADMIN);

        Product product = new Product("맛있는 반찬");

        ResponseEntity<Product> productResponse = template.withBasicAuth(email, "1234")
                .postForEntity("/api/admin/products", product, Product.class);

        assertThat(productResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(productResponse.getBody()).isNotNull();
    }

    @Test
    public void deleteTest() {
        //회원가입 진행
        String email = "doy@naver.com";

        ResponseEntity<Member> response = template.postForEntity("/api/members",
                MemberDto.defaultMemberDto().setEmail(email), Member.class);

        Member member = memberService.findByEmail(email);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(member).isNotNull();

        memberService.addRole(member, Role.ADMIN);

        Product product = new Product("맛있는 반찬");

        ResponseEntity<Product> productResponse = template.withBasicAuth(email, "1234")
                .postForEntity("/api/admin/products", product, Product.class);

        assertThat(productResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());
        ResponseEntity<Void> deleteResponse = template.withBasicAuth(email, "1234")
                .exchange("/api/admin/products/" + productResponse.getBody().getId(), HttpMethod.DELETE, httpEntity, Void.class);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
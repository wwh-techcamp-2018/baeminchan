package codesquad.promotion.web;

import codesquad.RestResponse;
import codesquad.promotion.domain.Promotion;
import codesquad.support.AcceptanceTest;
import codesquad.user.domain.Role;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PromotionAcceptanceTest extends AcceptanceTest {

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void createPromotion() {
        PromotionDto dto = new PromotionDto("http://blah.test.com/static/img/test.jpg");

        ResponseEntity<RestResponse<Promotion>> response = createPostResponseEntityWithUser(makeUser(Role.ADMIN),
                "/api/promotions", dto, getPromotionType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getData().getImgUrl()).isEqualTo(dto.getImgUrl());
    }

    @Test
    public void createPromotion_not_admin() {
        PromotionDto dto = new PromotionDto("http://blah.test.com/static/img/test.jpg");

        ResponseEntity<RestResponse<Promotion>> response = createPostResponseEntityWithUser(makeUser(Role.USER),
                "/api/promotions", dto, getPromotionType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void createPromotion_not_login() {
        PromotionDto dto = new PromotionDto("http://blah.test.com/static/img/test.jpg");

        ResponseEntity<RestResponse<Promotion>> response = createPostResponseEntity("/api/promotions", dto, getPromotionType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }


    private ParameterizedTypeReference<RestResponse<Promotion>> getPromotionType() {
        return new ParameterizedTypeReference<RestResponse<Promotion>>() {
        };
    }

    private ParameterizedTypeReference<RestResponse<List<Promotion>>> getPromotionListType() {
        return new ParameterizedTypeReference<RestResponse<List<Promotion>>>() {
        };
    }
}
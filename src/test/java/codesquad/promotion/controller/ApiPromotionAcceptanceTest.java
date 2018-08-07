package codesquad.promotion.controller;

import codesquad.RestResponse;
import codesquad.promotion.domain.Promotion;
import codesquad.promotion.domain.PromotionRepository;
import codesquad.support.AcceptanceTest;
import codesquad.user.domain.Role;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiPromotionAcceptanceTest extends AcceptanceTest {

    @Autowired
    private PromotionRepository promotionRepository;
    private Promotion saved;
    private PromotionDto dto;

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAll();
        promotionRepository.deleteAll();
        saved = promotionRepository.save(Promotion.builder().imgUrl("https://blah.com/test.jpg").build());
        dto = new PromotionDto("http://blah.test.com/static/img/test.jpg");
    }

    @Test
    public void createPromotion() {
        ResponseEntity<RestResponse<Promotion>> response = createPostResponseEntityWithUser(makeUser(Role.ADMIN),
                "/api/promotions", dto, getPromotionType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getData().getImgUrl()).isEqualTo(dto.getImgUrl());
    }

    @Test
    public void createPromotion_not_admin() {
        ResponseEntity<RestResponse<Promotion>> response = createPostResponseEntityWithUser(makeUser(Role.USER),
                "/api/promotions", dto, getPromotionType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void createPromotion_not_login() {
        ResponseEntity<RestResponse<Promotion>> response = createPostResponseEntity("/api/promotions", dto, getPromotionType());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void deletePromotion() {
        ResponseEntity<RestResponse<Promotion>> resp = deleteEntityWithUser(makeUser(Role.ADMIN), "/api/promotions/" + saved.getId(), getPromotionType());
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(promotionRepository.findById(saved.getId()).isPresent()).isFalse();
    }

    @Test
    public void deletePromotion_not_admin() {
        ResponseEntity<RestResponse<Promotion>> resp = deleteEntityWithUser(makeUser(Role.USER), "/api/promotions/" + saved.getId(), getPromotionType());
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void deletePromotion_not_login() {
        ResponseEntity<RestResponse<Promotion>> resp = deleteEntity("/api/promotions/" + saved.getId(), getPromotionType());
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
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
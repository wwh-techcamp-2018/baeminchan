package codesquad.controller;

import codesquad.domain.Promotion;
import codesquad.domain.PromotionRepository;
import codesquad.domain.User;
import codesquad.dto.PromotionDto;
import codesquad.web.HtmlFormDataBuilder;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.MultiValueMap;
import support.test.AcceptanceTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO
 * 1. promotion 백엔드
 * - 추가
 * - 조회 : startDate ~ endDate 안, deleted 가 false 애들
 * - 수정
 * - 삭제
 * 2. profile 설정
 * 3. 프론트엔트 transition 구현
 */
@Slf4j
@ActiveProfiles("test")
public class ApiPromotionControllerTest extends AcceptanceTest {
    private final Long TEST_PROMOTION_ID = 1L;

    @Autowired
    private PromotionRepository promotionRepository;

    private User defaultUser;

    @Before
    public void setUp() throws Exception {
        defaultUser = new User("javajigi@naver.com", "test33##");
    }

    @Test
    public void 프로모션_생성() {
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder
                .multipartFormData()
                .addParameter("title", "테스트 프로모션")
                .addParameter("priority", "2")
                .addParameter("startDate", LocalDate.now().toString())
                .addParameter("endDate", LocalDate.now().plusDays(7).toString())
                .addParameter("imageFile", new ClassPathResource("/static/img/img-main-visual-slide_1.jpg"))
                .build();
        ResponseEntity<Void> response = basicAuthTemplate(defaultUser).postForEntity("/api/promotions", request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void 프로모션_조회() {
        ResponseEntity<Iterable> response = basicAuthTemplate(defaultUser).getForEntity("/api/promotions", Iterable.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Lists.newArrayList(response.getBody()).size()).isEqualTo(2);
    }

    @Test
    public void 프로모션_수정() {
        PromotionDto updatedPromotionDto = new PromotionDto("절찬", 4, LocalDate.now(), LocalDate.now());
        ResponseEntity<Promotion> response = basicAuthTemplate(defaultUser).exchange(String.format("/api/promotions/%d", TEST_PROMOTION_ID), HttpMethod.PUT, new HttpEntity(updatedPromotionDto), Promotion.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getTitle()).isEqualTo(updatedPromotionDto.getTitle());
    }

    @Test
    public void 프로모션_삭제() {
        ResponseEntity<Void> response = basicAuthTemplate(defaultUser).exchange(String.format("/api/promotions/%d", TEST_PROMOTION_ID), HttpMethod.DELETE, new HttpEntity(new HttpHeaders()), Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(promotionRepository.findById(TEST_PROMOTION_ID).get().isDeleted()).isTrue();
    }
}

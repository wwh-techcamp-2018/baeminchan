package codesquad.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static org.assertj.core.api.Assertions.assertThat;

/**TODO
 * 서버
 * 1. Like 이용한 쿼리 - 컨트롤러에서 구현
 * 2. Like 쿼리 테스트
 * 3. spring boot swagger2 적용
 *
 * 프론트엔드
 * 1. 입력된 글자에 따른 반찬 리스트 요청
 * 2. 리스트 만들기
 * 3. 정확히 일치한 글자는 색깔 바꾸기
 * 4. 선택된 반찬은 배경색 바꾸기
 * 5. 키보드 리스너 추가해서 반찬 리스트 이동 가능하게 하기
 */
@Slf4j
public class ApiSideDishControllerTest extends AcceptanceTest {

    @Test
    public void 반찬검색() {
        String word = "된장";
        ResponseEntity<Iterable> response = template().getForEntity(String.format("/api/sidedishes/%s", word), Iterable.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.info("banchan : {}", response.getBody());
    }
}
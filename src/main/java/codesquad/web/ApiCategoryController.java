package codesquad.web;

import codesquad.domain.Category;
import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import codesquad.security.HttpSessionUtils;
import codesquad.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/categories")
public class ApiCategoryController {
    private static final Logger log = LoggerFactory.getLogger(ApiCategoryController.class);
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = {"", "/{parentId}"})
    public ResponseEntity<Category> create(@PathVariable(required = false) Long parentId, @RequestBody CategoryDto categoryDto, HttpSession session) {
        log.info("param : {}", categoryDto);
        // CategoryDto로 바꾸기 위해선 Category 객체안에 toCategoryDto 메소드가 필요한데 테이블과 관련된 도메인 객체안에서 이런 메소드를 정의해도 되는지?
        Category newCategory = categoryService.create(parentId, categoryDto, (User) session.getAttribute(HttpSessionUtils.USER_SESSION_KEY));
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
}


/**
 * Todo
 * 3. Interceptor 사용하여 권한별 접근 제어
 * 4. 관리자 페이지 만들어서 데이터 입력 해보기
 * (5. 관리자 전용 프로젝트 생성)
 *
 * Done
 *  * 1. 카테고리 등록하는 Contorller 생성
 *  * 2. DB로부터 데이터 가져와서 확인 테스트 코드 확인
 *
 */
package codesquad.controller;

import codesquad.domain.User;
import codesquad.dto.CategoryDto;
import codesquad.security.HttpSessionUtils;
import codesquad.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String list() {
        return "/admin/category";
    }

    @PostMapping("/categories")
    public ResponseEntity<Void> create(HttpSession session, @RequestBody CategoryDto categoryDto) {
        log.info("create : {}", categoryDto.toString());
        User user = (User)session.getAttribute(HttpSessionUtils.SESSIONED_USER);
        log.info("loginUser : {}", user.toString());
        categoryService.addCategory(user, categoryDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

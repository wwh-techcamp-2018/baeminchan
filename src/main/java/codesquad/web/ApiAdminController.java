package codesquad.web;

import codesquad.dto.CategoryPostDto;
import codesquad.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin")
public class ApiAdminController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Void> add(@RequestBody CategoryPostDto categoryPostDto) {
        //TODO admin 권한 확인 INTERCEPTOR
        categoryService.add(categoryPostDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/category")
    public ResponseEntity<Void> delete(@RequestBody CategoryPostDto categoryPostDto) {
        categoryService.delete(categoryPostDto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

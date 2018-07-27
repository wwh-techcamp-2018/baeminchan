package codesquad.web;

import codesquad.dto.CustomResponse;
import codesquad.dto.LoginDto;
import codesquad.dto.MemberDto;
import codesquad.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    public static final Logger log =  LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberService memberService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto, HttpSession session) {
        session.setAttribute("member", memberService.login(loginDto));
        return ResponseEntity.ok().body(new CustomResponse(200, "로그인에 성공했습니다."));
    }
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid MemberDto memberDto) {
        memberService.save(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(new CustomResponse(201, "회원가입이 완료되었습니다."));
    }
}

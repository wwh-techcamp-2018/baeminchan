package codesquad.web;

import codesquad.domain.Member;
import codesquad.dto.LoginDto;
import codesquad.dto.MemberDto;
import codesquad.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberController {
    public static final Logger log =  LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberService memberService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto, HttpSession session) {
        session.setAttribute("member", memberService.login(loginDto));
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public ResponseEntity<Member> create(@RequestBody @Valid MemberDto memberDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(memberDto));
    }
}

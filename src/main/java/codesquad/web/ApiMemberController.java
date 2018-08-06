package codesquad.web;

import codesquad.domain.Member;
import codesquad.dto.MemberJoinDto;
import codesquad.dto.MemberLoginDto;
import codesquad.security.HttpSessionUtils;
import codesquad.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/members")
public class ApiMemberController {
    @Resource
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody MemberJoinDto memberDto) {
        memberService.add(memberDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody MemberLoginDto memberDto, HttpSession session) {
        Member loginMember = memberService.login(memberDto);
        HttpSessionUtils.setMemberToSession(loginMember, session);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}

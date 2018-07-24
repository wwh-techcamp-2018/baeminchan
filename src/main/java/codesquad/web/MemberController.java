package codesquad.web;

import codesquad.domain.Member;
import codesquad.domain.MemberRepository;
import codesquad.dto.MemberDto;
import codesquad.support.exception.UnAuthenticationException;
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
    private MemberRepository memberRepository;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody MemberDto memberDto, HttpSession session) {
        Member member = memberRepository.findByEmail(memberDto.getEmail())
                .filter(m -> m.matchPassword(memberDto.getPassword()))
                .orElseThrow(() -> new UnAuthenticationException("유저 정보를 찾을 수 없습니다."));
        session.setAttribute("member", member);
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public ResponseEntity<Member> create(@RequestBody @Valid MemberDto memberDto) {
        log.debug("member dto, {}", memberDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberRepository.save(memberDto.toEntity()));
    }
}

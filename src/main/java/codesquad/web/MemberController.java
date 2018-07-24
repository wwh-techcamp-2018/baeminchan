package codesquad.web;

import codesquad.domain.Member;
import codesquad.domain.MemberRepository;
import codesquad.dto.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    public static final Logger log =  LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberRepository memberRepository;
    @PostMapping
    public ResponseEntity<Member> create(@RequestBody MemberDto memberDto) {
        log.debug("member dto, {}", memberDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberRepository.save(memberDto.toEntity()));
    }
}

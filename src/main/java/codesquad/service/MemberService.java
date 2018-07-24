package codesquad.service;

import codesquad.domain.Member;
import codesquad.domain.MemberRepository;
import codesquad.dto.MemberDto;
import codesquad.support.exception.UnAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member save(MemberDto memberDto) {
        Member member = memberDto.toEntity();
        return memberRepository.save(member);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new UnAuthenticationException("유저 정보를 찾을 수 없습니다."));
    }

    public Member login(MemberDto memberDto) {
        return memberRepository.findByEmail(memberDto.getEmail())
                .filter(m -> m.matchPassword(memberDto.getPassword()))
                .orElseThrow(() -> new UnAuthenticationException("유저 정보를 찾을 수 없습니다."));
    }
}

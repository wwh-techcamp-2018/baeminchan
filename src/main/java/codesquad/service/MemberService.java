package codesquad.service;

import codesquad.domain.Member;
import codesquad.domain.MemberRepository;
import codesquad.dto.MemberDto;
import codesquad.support.exception.UnAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Resource(name = "bcryptPasswordEncoder")
    private PasswordEncoder bCryptPasswordEncoder;

    public Member save(MemberDto memberDto) {
        Member member = memberDto.toEntity();
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new UnAuthenticationException("유저 정보를 찾을 수 없습니다."));
    }

    public Member login(MemberDto memberDto) {
        return memberRepository.findByEmail(memberDto.getEmail())
                .filter(m -> bCryptPasswordEncoder.matches(memberDto.getPassword(), m.getPassword()))
                .orElseThrow(() -> new UnAuthenticationException("유저 정보를 찾을 수 없습니다."));
    }
}

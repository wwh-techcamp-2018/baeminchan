package codesquad.service;

import codesquad.domain.Member;
import codesquad.domain.MemberRepository;
import codesquad.dto.LoginDto;
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
    @Resource(name = "bCryptPasswordEncoder")
    private PasswordEncoder bCryptPasswordEncoder;

    public Member save(MemberDto memberDto) {
        Member member = Member.fromDto(memberDto, bCryptPasswordEncoder);
        return memberRepository.save(member);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new UnAuthenticationException("유저 정보를 찾을 수 없습니다."));
    }

    public Member login(LoginDto loginDto) {
        return memberRepository.findByEmail(loginDto.getEmail())
                .filter(m -> bCryptPasswordEncoder.matches(loginDto.getPassword(), m.getPassword()))
                .orElseThrow(() -> new UnAuthenticationException("유저 정보를 찾을 수 없습니다."));
    }
}

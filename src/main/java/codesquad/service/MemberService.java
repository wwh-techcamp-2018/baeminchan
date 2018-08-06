package codesquad.service;

import codesquad.domain.Member;
import codesquad.repository.MemberRepository;
import codesquad.dto.LoginDto;
import codesquad.dto.MemberDto;
import codesquad.support.Role;
import codesquad.support.exception.UnAuthenticationException;
import codesquad.validation.ValidationMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
        return memberRepository.findByEmail(email).orElseThrow(() -> new UnAuthenticationException(ValidationMessageUtil.USER_NOT_FOUND));
    }

    public Member login(LoginDto loginDto) {
        return memberRepository.findByEmail(loginDto.getEmail())
                .filter(m -> bCryptPasswordEncoder.matches(loginDto.getPassword(), m.getPassword()))
                .orElseThrow(() -> new UnAuthenticationException(ValidationMessageUtil.USER_NOT_FOUND));
    }

    public Member addRole(Member member, Role role) {
        member.addRole(role);
        return memberRepository.save(member);
    }
}

package codesquad.service;

import codesquad.domain.Member;
import codesquad.dto.MemberJoinDto;
import codesquad.dto.MemberLoginDto;
import codesquad.exception.UnAuthenticationException;
import codesquad.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("memberService")
public class MemberService {
    @Resource
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    public Member add(MemberJoinDto memberDto) {
        return memberRepository.save(memberDto.toEntity(bcryptPasswordEncoder));
    }

    public Member login(MemberLoginDto memberDto) {
        Member dbMember = findByEmail(memberDto.getEmail());
        if (!dbMember.matchPassword(memberDto.getPassword(), bcryptPasswordEncoder)) {
            throw new UnAuthenticationException(UnAuthenticationException.NOT_MATCH_PASSWORD);
        }
        return dbMember;
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new UnAuthenticationException(UnAuthenticationException.NOT_EXIST_EMAIL));
    }
}

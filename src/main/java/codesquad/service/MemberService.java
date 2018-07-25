package codesquad.service;

import codesquad.domain.Member;
import codesquad.dto.MemberDto;
import codesquad.exception.UnAuthenticationException;
import codesquad.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("memberService")
public class MemberService {

    @Resource
    private MemberRepository memberRepository;

    public Member add(MemberDto memberDto) {
        //TODO memberDTO validation 체크하는 부분
        return memberRepository.save(memberDto.toEntity());
    }

    public Member login(MemberDto memberDto) {
        Member dbMember = findByEmail(memberDto.getEmail());
        if (!dbMember.matchPassword(memberDto.getPassword())) {
            throw new UnAuthenticationException(UnAuthenticationException.NOT_MATCH_PASSWORD);
        }
        return dbMember;
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new UnAuthenticationException(UnAuthenticationException.NOT_EXIST_EMAIL));
    }
}

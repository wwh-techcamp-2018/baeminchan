package codesquad.service;

import codesquad.domain.Member;
import codesquad.dto.MemberDto;
import codesquad.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("memberService")
public class MemberService {

    @Resource
    private MemberRepository memberRepository;

    public Member add(MemberDto memberDto) {
        //TODO memberDTO validation 체크하는 부분
        return memberRepository.save(memberDto.toMember());
    }
}

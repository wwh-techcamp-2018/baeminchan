package codesquad.service;

import codesquad.domain.Member;
import codesquad.domain.MemberTest;
import codesquad.dto.MemberDto;
import codesquad.dto.MemberDtoTest;
import codesquad.exception.UnAuthenticationException;
import codesquad.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;


//    @Test
//    public void add() {
//        memberService = new MemberService();
//        MemberDto memberDto = new MemberDto();
//
//        //TODO 입력한 내용의 validation check
//        memberService.add()
//    }

    @Test
    public void login_success() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("dooho@woowahan.com", "1234password");

        Member dbMember = MemberTest.newMember("dooho@woowahan.com", "1234password");
        when(memberRepository.findByEmail(testMemberDto.getEmail())).thenReturn(Optional.of(dbMember));
        Member loginMember = memberService.login(testMemberDto);

        assertThat(loginMember).isEqualTo(dbMember);
    }

    @Test(expected = UnAuthenticationException.class)
    public void login_fail_wrong_password() {
        MemberDto testMemberDto = MemberDtoTest.newMemberDto("dooho@woowahan.com", "1234wrong");

        Member dbMember = MemberTest.newMember("dooho@woowahan.com", "1234password");
        when(memberRepository.findByEmail(testMemberDto.getEmail())).thenReturn(Optional.of(dbMember));
        Member loginMember = memberService.login(testMemberDto);
    }

    @Test(expected = UnAuthenticationException.class)
    public void findByEmail_not_found() {
        memberService.findByEmail("dooho@woowahan.com");
    }
}
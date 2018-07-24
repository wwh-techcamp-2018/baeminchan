package codesquad.service;

import codesquad.domain.Member;
import codesquad.domain.MemberRepository;
import codesquad.dto.MemberDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberService memberService;

    @Test
    public void createTest() {
        MemberDto memberDto = new MemberDto("pobi@naver.com", "1234", "pobi","01012341234");
        memberService.save(memberDto);
        verify(memberRepository).save(memberDto.toEntity());
    }

    @Test
    public void loginTest() {
        Member member = new Member("javajigi@naver.com", "123123", "pobi", "01012341234");
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail(member.getEmail());
        when(memberRepository.findByEmail(member.getEmail())).thenReturn(Optional.ofNullable(member));
        memberDto.setPassword(member.getPassword());
        assertThat(memberService.login(memberDto)).isEqualTo(member);
    }
}

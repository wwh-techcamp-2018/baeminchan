package codesquad.service;

import codesquad.domain.Member;
import codesquad.domain.MemberRepository;
import codesquad.dto.LoginDto;
import codesquad.dto.MemberDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {
    public static final Logger log =  LoggerFactory.getLogger(MemberServiceTest.class);
    @Mock
    private MemberRepository memberRepository;
    @Spy
    private PasswordEncoder mockPasswordEncoder = new MockPasswordEncoder();
    @InjectMocks
    private MemberService memberService;

    @Test
    public void createTest() {
        MemberDto memberDto = MemberDto.defaultMemberDto();
        memberService.save(memberDto);
        Member member = Member.fromDto(memberDto, mockPasswordEncoder);
        verify(memberRepository).save(member);
    }

    @Test
    public void loginTest() {
        String password = "123123";
        Member member = new Member("javajigi@naver.com", mockPasswordEncoder.encode(password), "pobi", "01012341234");
        MemberDto memberDto = new MemberDto();
        LoginDto loginDto = new LoginDto(member.getEmail(), password);
        when(memberRepository.findByEmail(member.getEmail())).thenReturn(Optional.ofNullable(member));
        assertThat(memberService.login(loginDto)).isEqualTo(member);
    }

    private class MockPasswordEncoder implements PasswordEncoder {

        @Override
        public String encode(CharSequence rawPassword) {
            return new StringBuilder(rawPassword).reverse().toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return encode(rawPassword).equals(encodedPassword);
        }
    }
}

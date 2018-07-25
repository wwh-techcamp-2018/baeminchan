package codesquad.support;

import codesquad.dto.MemberDto;

public class MemberDtoBuilder {
    private MemberDto memberDto;

    private MemberDtoBuilder(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    public static MemberDtoBuilder builder() {
        return new MemberDtoBuilder(new MemberDto("pobi@naver.com", "1234", "pobi", "01012341234"));
    }

    public MemberDtoBuilder email(String email) {
        this.memberDto.setEmail(email);
        return this;
    }

    public MemberDtoBuilder password(String password) {
        this.memberDto.setPassword(password);
        return this;
    }

    public MemberDtoBuilder username(String username) {
        this.memberDto.setUsername(username);
        return this;
    }

    public MemberDtoBuilder phoneNumber(String phoneNumber) {
        this.memberDto.setPhoneNumber(phoneNumber);
        return this;
    }

    public MemberDto build() {
        return memberDto;
    }
}

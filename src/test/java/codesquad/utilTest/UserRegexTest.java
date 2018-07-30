package codesquad.utilTest;

import codesquad.domain.Regrex;
import codesquad.domain.User;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

public class UserRegexTest {
    SoftAssertions softly;

    @Before
    public void setUp() {
       softly = new SoftAssertions();
    }
    @Test
    public void phoneNumber_검사(){
        final String phoneNumberRegex = Regrex.PHONE_NUMBER.getRegrexStr();
        Pattern pattern = Pattern.compile(phoneNumberRegex);

        softly.assertThat(pattern.matcher("01*-1234-1234").matches()).as("숫자 아닌 입력").isFalse();
        softly.assertThat(pattern.matcher("011-12340-1234").matches()).as("숫자 갯수 안맞을때").isFalse();
        softly.assertThat(pattern.matcher("123-1234-1234").matches()).as("01로 시작하지 않을때").isFalse();
        softly.assertThat(pattern.matcher("010-12341234").matches()).as("하이픈 없을때").isFalse();
        softly.assertThat(pattern.matcher("010-1234-1234").matches()).as("정상").isTrue();

        softly.assertAll();

    }

    @Test
    public void password_검사(){
        final String passwordRegex = Regrex.PASSWORD.getRegrexStr();
        Pattern pattern = Pattern.compile(passwordRegex);

        softly.assertThat(pattern.matcher("*abcdAB12334").matches()).as("특수문자").isFalse();

        softly.assertAll();

    }

    @Test
    public void name_검사(){
        final String nameRegex = Regrex.NAME.getRegrexStr();
        Pattern pattern = Pattern.compile(nameRegex);
        softly.assertThat(pattern.matcher("가나다").matches()).as("성공").isTrue();

        softly.assertThat(pattern.matcher("abcd").matches()).as("영어").isFalse();

        softly.assertAll();

    }
}

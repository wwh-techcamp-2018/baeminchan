package codesquad.utilTest;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.regex.Pattern;

public class UserRegexTest {

    @Test
    public void email_검사(){

        final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(emailRegex);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(pattern.matcher("a@a!sdf.com").matches()).as("특수문자 _ 제외").isFalse();
        softAssertions.assertThat(pattern.matcher("asdfa.com").matches()).as("@ 없을 때").isFalse();
        softAssertions.assertThat(pattern.matcher("a@asdfafacom").matches()).as(". 없을 때").isFalse();

        softAssertions.assertAll();

    }

    @Test
    public void phoneNumber_검사(){
        final String phoneNumberRegex = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
        Pattern pattern = Pattern.compile(phoneNumberRegex);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(pattern.matcher("01*-1234-1234").matches()).as("숫자 아닌 입력").isFalse();
        softAssertions.assertThat(pattern.matcher("011-12340-1234").matches()).as("숫자 갯수 안맞을때").isFalse();
        softAssertions.assertThat(pattern.matcher("123-1234-1234").matches()).as("01로 시작하지 않을때").isFalse();
        softAssertions.assertThat(pattern.matcher("010-12341234").matches()).as("하이픈 없을때").isFalse();
        softAssertions.assertThat(pattern.matcher("010-1234-1234").matches()).as("정상").isTrue();

        softAssertions.assertAll();

    }

    @Test
    public void password_검사(){
        final String passwordRegex = "^[a-zA-Z0-9]\\w{8,20}$";
        Pattern pattern = Pattern.compile(passwordRegex);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(pattern.matcher("*abcdAB12334").matches()).as("특수문자").isFalse();
        softAssertions.assertThat(pattern.matcher("a").matches()).as("길이 짧은것").isFalse();
        softAssertions.assertThat(pattern.matcher("abcdAB12334abcdAB12334abcdAB12334").matches()).as("길이 긴것").isFalse();

        softAssertions.assertAll();

    }

    @Test
    public void name_검사(){
        final String nameRegex = "^[가-힣]{2,4}";
        Pattern pattern = Pattern.compile(nameRegex);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(pattern.matcher("가나다").matches()).as("성공").isTrue();

        softAssertions.assertThat(pattern.matcher("abcd").matches()).as("영어").isFalse();
        softAssertions.assertThat(pattern.matcher("가").matches()).as("길이 짧은 ").isFalse();
        softAssertions.assertThat(pattern.matcher("가나다라마").matches()).as("길이 길은").isFalse();

        softAssertions.assertAll();

    }
}

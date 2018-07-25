package codesquad.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserValidationTest {
    private static final Logger log = LoggerFactory.getLogger(UserValidationTest.class);

    private static Validator validator;
    private User user;
    SoftAssertions softly;

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Before
    public void setUp() {
        user = new User("abcde@gmail.com", "password15", "password15","가나다", "010-123-1234");
        softly = new SoftAssertions();
    }
    @Test
    public void validation_성공(){

        Set<ConstraintViolation<User>> constraintViolcations = validator.validate(user);
        log.debug(constraintViolcations.size()+"");

        for (ConstraintViolation<User> constraintViolation : constraintViolcations) {
            log.debug("violation error message : {}", constraintViolation.getMessage());
        }
    }
    @Test
    public void email_검사_fail() {

        user.setEmail("aasdfafacom");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("@ 없을 때");

        user.setEmail("aasdfafac@o@m");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("@ 여러개");

        user.setEmail("a”b(c)d,e:f;g<h>i[j\\k]l@example.com");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("email 앞부분에 허용되지 않은 특수문자");

        user.setEmail("ab@asd faacom");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("스페이스 포함");

        user.setEmail("1234567890123456789012345678901234567890123456789012345678901234+x@example.com");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("아이디 64글자 이상");


        softly.assertAll();

    }

    @Test
    public void phoneNumber_검사_fail() {
        user.setPhoneNumber("110-4333-4441");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("01*로 시작안함");
        user.setPhoneNumber("01*-1234-1234");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("틀수문자포함");
        user.setPhoneNumber("011-12340-1234");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("중간값갯수맞지않음");
        user.setPhoneNumber("010-12341234");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("하이픈없음");

        softly.assertAll();

    }

    @Test
    public void password_검사_fail() {
        user.setPassword("1239dfhhf!@#");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("특수문자있음");
        user.setPassword("a1e3");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("길이 짧은것");
        user.setPassword("zcvbnmqwertyuiop12378950");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("길이긴것");

        softly.assertAll();

    }

    @Test
    public void name_검사_fail() {

        user.setName("kyt");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("한글아닐때");
        user.setName("연");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("짧을때");
        user.setName("김연태입니다");
        softly.assertThat(validator.validate(user).size()).isEqualTo(1).as("길 때");

        softly.assertAll();

    }

    @Test
    public void password일치_실패() {
        softly.assertThat(user.isEqualPassword()).isTrue().as("비밀번호 같음");
        user.setPassword("1239dfhhf");
        user.setPasswordCheck("1239df");
        softly.assertThat(user.isEqualPassword()).isFalse().as("비밀번호 다름");
        softly.assertAll();
    }

    @Test
    public void password인코딩() {
        FixedPasswordEncoder encoder = new FixedPasswordEncoder();
        user.encrypt(encoder);
        User newUser = new User("abcde@gmail.com", "password15", "password15","가나다", "010-123-1234");
        softly.assertThat(user.matchEncodedPassword(encoder, newUser)).isTrue();

        softly.assertAll();

    }
}

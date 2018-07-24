package codesquad.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

    }

    @Before
    public void setUp() {
        user = new User("abcde@gmail.com", "password15", "가나다", "010-123-1234");
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


        SoftAssertions softAssertions = new SoftAssertions();
        user.setEmail("a@a!sdf.com");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("특수문자 _ 제외");
        user.setEmail("asdfa.com");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("@ 없을 때");

        user.setEmail("a@asdfafacom");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as(". 없을 때");

        softAssertions.assertAll();

    }

    @Test
    public void phoneNumber_검사_fail() {
        SoftAssertions softAssertions = new SoftAssertions();

        user.setPhoneNumber("110-4333-4441");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("01*로 시작안함");
        user.setPhoneNumber("01*-1234-1234");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("틀수문자포함");
        user.setPhoneNumber("011-12340-1234");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("중간값갯수맞지않음");
        user.setPhoneNumber("010-12341234");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("하이픈없음");

        softAssertions.assertAll();

    }

    @Test
    public void password_검사_fail() {
        SoftAssertions softAssertions = new SoftAssertions();
        user.setPassword("1239dfhhf!@#");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("특수문자있음");
        user.setPassword("a1e3");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("길이 짧은것");
        user.setPassword("zcvbnmqwertyuiop12378950");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("길이긴것");

        softAssertions.assertAll();

    }

    @Test
    public void name_검사_fail() {
        SoftAssertions softAssertions = new SoftAssertions();
        user.setName("kyt");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("한글아닐때");
        user.setName("연");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("짧을때");

        user.setName("김연태입니다");
        softAssertions.assertThat(validator.validate(user).size()).isEqualTo(1).as("길 때");

        softAssertions.assertAll();

    }
}

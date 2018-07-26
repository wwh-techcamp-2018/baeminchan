package codesquad.validate;

public class Patterns {
    public static final String USER_ID = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
    public static final String NAME = "^[가-힣]*$";
    public static final String PHONE_NUMBER = "^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$";
}

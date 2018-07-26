package codesquad.utils;

public class Regex {
    public static final String EMAIL = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    public static final String USERNAME = "[가-힣]{2,16}|[a-zA-Z]{2,16}";
    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$";
    public static final String PHONE ="(01[016789])-(\\d{3,4})-(\\d{4})";
}

package codesquad.util;

public class Regex {
    public static final String EMAIL = "\\p{Alnum}+[.\\p{Alnum}]+@\\p{Alnum}+\\.\\p{Alpha}+[.]?\\p{Alpha}+";
    public static final String PASSWORD = "^.*(?=.*\\d)(?=.*\\p{Alpha}).*$";
    public static final String PHONE_NUM = "^\\d{3}-\\d{3,4}-\\d{4}$";
}

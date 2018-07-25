package codesquad.domain;

public enum  DomainField {
    USER_EMAIL("email"),
    USER_PASSWORD("password"),
    USER_PASSWORD_CONFIRM("passwordConfirm"),
    USER_PHONENUMBER("phoneNumber"),
    USER_NAME("name");

    private String fieldName;

    DomainField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}

package codesquad.domain;

import java.util.Arrays;

public enum  DomainField {
    USER_EMAIL("email"),
    USER_PASSWORD("password"),
    USER_PHONENUBER("phoneNumber"),
    USER_NAME("name");

    private String fieldName;

    DomainField(String fieldName) {
        this.fieldName = fieldName;
    }

    public static DomainField value(String fieldName) {
        return Arrays.stream(DomainField.values())
                .filter(domainField -> domainField.fieldName.equals(fieldName))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        // TODO: Exception customize is needed.
    }

    public String getFieldName() {
        return fieldName;
    }
}

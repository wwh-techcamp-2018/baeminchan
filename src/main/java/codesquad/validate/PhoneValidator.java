package codesquad.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String>{

    private String phoneRegex ="(01[016789])-(\\d{3,4})-(\\d{4})";
    @Override
    public void initialize(Phone phone) {

    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext cxt) {
//        return field != null && field.matches("[0-9]+")
//                && (field.length() > 8) && (field.length() < 14);
        return field != null && field.matches(phoneRegex);
    }
}

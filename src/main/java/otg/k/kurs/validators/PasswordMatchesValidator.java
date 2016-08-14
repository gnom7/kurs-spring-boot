package otg.k.kurs.validators;

import otg.k.kurs.dto.AccountDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches passwordMatches) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        AccountDto user = (AccountDto) object;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}

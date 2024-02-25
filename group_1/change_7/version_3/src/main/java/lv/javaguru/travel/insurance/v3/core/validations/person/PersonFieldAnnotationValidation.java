package lv.javaguru.travel.insurance.v3.core.validations.person;

import lv.javaguru.travel.insurance.v3.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.v3.core.util.Placeholder;
import lv.javaguru.travel.insurance.v3.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
class PersonFieldAnnotationValidation extends TravelPersonFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public List<ValidationErrorDTO> validateList(AgreementDTO agreement, PersonDTO person) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<PersonDTO>> violations = validator.validate(person);
        if (violations.isEmpty()) {
            return List.of();
        } else {
            List<ValidationErrorDTO> errors = new ArrayList<>();
            for (ConstraintViolation<PersonDTO> violation : violations) {
                String fieldName = violation.getPropertyPath().toString();
                String fieldMessage = violation.getMessage();
                Placeholder placeholder1 = new Placeholder("FIELD_NAME", fieldName);
                Placeholder placeholder2 = new Placeholder("FIELD_MESSAGE", fieldMessage);
                errors.add(errorFactory.buildError("ERROR_CODE_22", List.of(placeholder1, placeholder2)));
            }
            return errors;
        }
    }

}

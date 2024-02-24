package lv.javaguru.travel.insurance.v1.core.validations.person;

import lv.javaguru.travel.insurance.v1.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v1.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.v1.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.v1.core.util.DateTimeUtil;
import lv.javaguru.travel.insurance.v1.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

//@Component
class PersonBirthDateInThePastValidation extends TravelPersonFieldValidationImpl {

    @Autowired private DateTimeUtil dateTimeUtil;
    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        Date personBirthDate = person.getPersonBirthDate();
        Date currentDateTime = dateTimeUtil.getCurrentDateTime();
        return (personBirthDate != null && personBirthDate.after(currentDateTime))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }

}

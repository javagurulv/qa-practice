package lv.javaguru.travel.insurance.v3.core.validations.person;

import lv.javaguru.travel.insurance.v3.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.v3.core.util.Placeholder;
import lv.javaguru.travel.insurance.v3.core.validations.ValidationErrorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
class PersonCodeFormatValidation extends TravelPersonFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        if (!isPersonCodeNullOrBlank(person) && !isValidFormat(person)) {
            Placeholder placeholder = new Placeholder("PERSONAL_CODE", person.getPersonCode());
            return Optional.of(errorFactory.buildError("ERROR_CODE_19", List.of(placeholder)));
        } else {
            return Optional.empty();
        }
    }

    private boolean isPersonCodeNullOrBlank(PersonDTO person) {
        return person.getPersonCode() == null || person.getPersonCode().isBlank();
    }

    private boolean isValidFormat(PersonDTO person) {
        String patternString = "\\d{6}-\\d{5}$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(person.getPersonCode());
        return matcher.matches();
    }

}

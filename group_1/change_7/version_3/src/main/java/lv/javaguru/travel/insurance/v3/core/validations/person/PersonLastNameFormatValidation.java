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
class PersonLastNameFormatValidation extends TravelPersonFieldValidationImpl {

    @Autowired private ValidationErrorFactory errorFactory;

    @Override
    public Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person) {
        if (!isPersonLastNameNullOrBlank(person) && !isValidFormat(person)) {
            Placeholder placeholder = new Placeholder("PERSON_LAST_NAME", person.getPersonLastName());
            return Optional.of(errorFactory.buildError("ERROR_CODE_21", List.of(placeholder)));
        } else {
            return Optional.empty();
        }
    }

    private boolean isPersonLastNameNullOrBlank(PersonDTO person) {
        return person.getPersonLastName() == null || person.getPersonLastName().isBlank();
    }

    private boolean isValidFormat(PersonDTO person) {
        String patternString = "^[A-Za-z]+([ -][A-Za-z]+)*$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(person.getPersonLastName());
        return matcher.matches();
    }

}

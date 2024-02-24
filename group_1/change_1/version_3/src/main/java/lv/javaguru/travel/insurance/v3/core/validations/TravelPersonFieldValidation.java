package lv.javaguru.travel.insurance.v3.core.validations;

import lv.javaguru.travel.insurance.v3.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.ValidationErrorDTO;

import java.util.List;
import java.util.Optional;

public interface TravelPersonFieldValidation {

    Optional<ValidationErrorDTO> validate(AgreementDTO agreement, PersonDTO person);

    List<ValidationErrorDTO> validateList(AgreementDTO agreement, PersonDTO person);

}

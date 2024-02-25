package lv.javaguru.travel.insurance.v2.core.validations;

import lv.javaguru.travel.insurance.v2.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v2.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelAgreementValidator {

    List<ValidationErrorDTO> validate(AgreementDTO agreement);

}

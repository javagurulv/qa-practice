package lv.javaguru.travel.insurance.v3.core.validations;

import lv.javaguru.travel.insurance.v3.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelAgreementIdValidator {

    List<ValidationErrorDTO> validate(Long agreementId);

}

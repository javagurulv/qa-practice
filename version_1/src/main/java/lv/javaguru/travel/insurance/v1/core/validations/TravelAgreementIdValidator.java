package lv.javaguru.travel.insurance.v1.core.validations;

import lv.javaguru.travel.insurance.v1.core.api.dto.ValidationErrorDTO;

import java.util.List;

public interface TravelAgreementIdValidator {

    List<ValidationErrorDTO> validate(Long agreementId);

}

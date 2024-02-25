package lv.javaguru.travel.insurance.v3.core.validations;

import lv.javaguru.travel.insurance.v3.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.v3.core.domain.entities.AgreementEntity;
import lv.javaguru.travel.insurance.v3.core.repositories.entities.AgreementEntityRepository;
import lv.javaguru.travel.insurance.v3.core.util.Placeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class TravelAgreementIdValidatorImpl implements TravelAgreementIdValidator {

    @Autowired private ValidationErrorFactory errorFactory;
    @Autowired private AgreementEntityRepository agreementEntityRepository;


    @Override
    public List<ValidationErrorDTO> validate(Long agreementId) {
        List<ValidationErrorDTO> errors = new ArrayList<>();
        if (agreementId == null) {
            errors.add(errorFactory.buildError("ERROR_CODE_17"));
        } else {
            Optional<AgreementEntity> agreementOpt = agreementEntityRepository.findById(agreementId);
            if (!agreementOpt.isPresent()) {
                Placeholder placeholder = new Placeholder("AGREEMENT_ID", agreementId.toString());
                errors.add(errorFactory.buildError("ERROR_CODE_18", List.of(placeholder)));
            }
        }
        return errors;
    }

}

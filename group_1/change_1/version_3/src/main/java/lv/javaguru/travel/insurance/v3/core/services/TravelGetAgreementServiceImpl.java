package lv.javaguru.travel.insurance.v3.core.services;

import lv.javaguru.travel.insurance.v3.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.v3.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.v3.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.v3.core.validations.TravelAgreementIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
class TravelGetAgreementServiceImpl implements TravelGetAgreementService {

    @Autowired private TravelAgreementIdValidator agreementIdValidator;
    @Autowired private AgreementDTOLoader agreementDTOLoader;

    @Override
    public TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command) {
        List<ValidationErrorDTO> errors = agreementIdValidator.validate(command.getAgreementId());
        return errors.isEmpty()
                ? buildResponse(command)
                : buildResponse(errors);
    }

    private TravelGetAgreementCoreResult buildResponse(List<ValidationErrorDTO> errors) {
        return new TravelGetAgreementCoreResult(errors);
    }

    private TravelGetAgreementCoreResult buildResponse(TravelGetAgreementCoreCommand command) {
        AgreementDTO dto = agreementDTOLoader.load(command.getAgreementId());
        TravelGetAgreementCoreResult coreResult = new TravelGetAgreementCoreResult();
        coreResult.setAgreement(dto);
        return coreResult;
    }

}

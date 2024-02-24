package lv.javaguru.travel.insurance.v1.dto.v1;

import lv.javaguru.travel.insurance.v1.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.v1.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.v1.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.v1.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.v1.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v1.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.v1.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.v1.dto.RiskPremium;
import lv.javaguru.travel.insurance.v1.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoV1Converter {

    public TravelCalculatePremiumCoreCommand buildCalculatePremiumCoreCommand(TravelCalculatePremiumRequestV1 request) {
        AgreementDTO agreement = buildAgreement(request);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    public TravelGetAgreementCoreCommand buildGetAgreementCoreCommand(Long agreementId) {
        return new TravelGetAgreementCoreCommand(agreementId);
    }

    public TravelGetAgreementResponseV1 buildGetAgreementResponse(TravelGetAgreementCoreResult coreResult) {
        return coreResult.hasErrors()
                ? buildGetAgreementResponseWithErrors(coreResult.getErrors())
                : buildGetAgreementSuccessfulResponse(coreResult);
    }


    public TravelCalculatePremiumResponseV1 buildCalculatePremiumResponse(TravelCalculatePremiumCoreResult coreResult) {
        return coreResult.hasErrors()
                ? buildCalculatePremiumResponseWithErrors(coreResult.getErrors())
                : buildCalculatePremiumSuccessfulResponse(coreResult);
    }

    private TravelGetAgreementResponseV1 buildGetAgreementResponseWithErrors(List<ValidationErrorDTO> coreErrors) {
        List<ValidationError> errors = transformValidationErrors(coreErrors);
        return new TravelGetAgreementResponseV1(errors);
    }

    private TravelCalculatePremiumResponseV1 buildCalculatePremiumResponseWithErrors(List<ValidationErrorDTO> coreErrors) {
        List<ValidationError> errors = transformValidationErrors(coreErrors);
        return new TravelCalculatePremiumResponseV1(errors);
    }

    private List<ValidationError> transformValidationErrors(List<ValidationErrorDTO> coreErrors) {
        return coreErrors.stream()
                .map(error -> new ValidationError(error.getErrorCode(), error.getDescription()))
                .collect(Collectors.toList());
    }

    private TravelCalculatePremiumResponseV1 buildCalculatePremiumSuccessfulResponse(TravelCalculatePremiumCoreResult coreResult) {
        AgreementDTO agreement = coreResult.getAgreement();
        TravelCalculatePremiumResponseV1 response = new TravelCalculatePremiumResponseV1();
        response.setAgreementId(agreement.getAgreementId());
        response.setPersonFirstName(agreement.getPersons().get(0).getPersonFirstName());
        response.setPersonLastName(agreement.getPersons().get(0).getPersonLastName());
        response.setPersonCode(agreement.getPersons().get(0).getPersonCode());
        response.setPersonBirthDate(agreement.getPersons().get(0).getPersonBirthDate());
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setMedicalRiskLimitLevel(agreement.getPersons().get(0).getMedicalRiskLimitLevel());
        response.setAgreementPremium(agreement.getAgreementPremium());

        PersonDTO person = agreement.getPersons().get(0);
        List<RiskPremium> riskPremiums = person.getRisks().stream()
                .map(riskDTO -> new RiskPremium(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .toList();
        response.setRisks(riskPremiums);

        return response;
    }

    private TravelGetAgreementResponseV1 buildGetAgreementSuccessfulResponse(TravelGetAgreementCoreResult coreResult) {
        AgreementDTO agreement = coreResult.getAgreement();
        TravelGetAgreementResponseV1 response = new TravelGetAgreementResponseV1();
        response.setAgreementId(agreement.getAgreementId());
        response.setPersonFirstName(agreement.getPersons().get(0).getPersonFirstName());
        response.setPersonLastName(agreement.getPersons().get(0).getPersonLastName());
        response.setPersonCode(agreement.getPersons().get(0).getPersonCode());
        response.setPersonBirthDate(agreement.getPersons().get(0).getPersonBirthDate());
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setMedicalRiskLimitLevel(agreement.getPersons().get(0).getMedicalRiskLimitLevel());
        response.setAgreementPremium(agreement.getAgreementPremium());

        PersonDTO person = agreement.getPersons().get(0);
        List<RiskPremium> riskPremiums = person.getRisks().stream()
                .map(riskDTO -> new RiskPremium(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .toList();
        response.setRisks(riskPremiums);

        return response;
    }


    private PersonDTO buildPerson(TravelCalculatePremiumRequestV1 request) {
        PersonDTO person = new PersonDTO();
        person.setPersonFirstName(request.getPersonFirstName());
        person.setPersonLastName(request.getPersonLastName());
        person.setPersonCode(request.getPersonCode());
        person.setPersonBirthDate(request.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(request.getMedicalRiskLimitLevel());
        return person;
    }

    private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV1 request) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setCountry(request.getCountry());
        agreement.setSelectedRisks(request.getSelectedRisks());

        PersonDTO person = buildPerson(request);
        agreement.setPersons(List.of(person));

        return agreement;
    }


}

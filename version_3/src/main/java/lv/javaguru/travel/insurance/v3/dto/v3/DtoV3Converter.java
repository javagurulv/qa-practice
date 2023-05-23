package lv.javaguru.travel.insurance.v3.dto.v3;

import lv.javaguru.travel.insurance.v3.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.v3.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.v3.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.v3.dto.RiskPremium;
import lv.javaguru.travel.insurance.v3.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoV3Converter {

    public TravelCalculatePremiumCoreCommand buildCoreCommand(TravelCalculatePremiumRequestV3 request) {
        AgreementDTO agreement = buildAgreement(request);
        return new TravelCalculatePremiumCoreCommand(agreement);
    }

    public TravelCalculatePremiumResponseV3 buildResponse(TravelCalculatePremiumCoreResult coreResult) {
        return coreResult.hasErrors()
                ? buildResponseWithErrors(coreResult.getErrors())
                : buildSuccessfulResponse(coreResult);
    }

    private TravelCalculatePremiumResponseV3 buildResponseWithErrors(List<ValidationErrorDTO> coreErrors) {
        List<ValidationError> errors = transformValidationErrorsToV2(coreErrors);
        return new TravelCalculatePremiumResponseV3(errors);
    }

    private List<ValidationError> transformValidationErrorsToV2(List<ValidationErrorDTO> coreErrors) {
        return coreErrors.stream()
                .map(error -> new ValidationError(error.getErrorCode(), error.getDescription()))
                .collect(Collectors.toList());
    }

    private TravelCalculatePremiumResponseV3 buildSuccessfulResponse(TravelCalculatePremiumCoreResult coreResult) {
        AgreementDTO agreement = coreResult.getAgreement();
        TravelCalculatePremiumResponseV3 response = new TravelCalculatePremiumResponseV3();
        response.setAgreementDateFrom(agreement.getAgreementDateFrom());
        response.setAgreementDateTo(agreement.getAgreementDateTo());
        response.setCountry(agreement.getCountry());
        response.setAgreementPremium(agreement.getAgreementPremium());

        List<PersonResponseDTO> personResponseDTOS = agreement.getPersons().stream()
                .map(this::buildPersonFromResponse)
                .toList();
        response.setPersons(personResponseDTOS);

        return response;
    }

    private PersonResponseDTO buildPersonFromResponse(PersonDTO personDTO) {
        PersonResponseDTO person = new PersonResponseDTO();
        person.setPersonFirstName(personDTO.getPersonFirstName());
        person.setPersonLastName(personDTO.getPersonLastName());
        person.setPersonCode(personDTO.getPersonCode());
        person.setPersonBirthDate(personDTO.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(personDTO.getMedicalRiskLimitLevel());

        person.setPersonPremium(personDTO.getRisks().stream()
                .map(RiskDTO::getPremium)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        person.setPersonRisks(personDTO.getRisks().stream()
                .map(riskDTO -> new RiskPremium(riskDTO.getRiskIc(), riskDTO.getPremium()))
                .collect(Collectors.toList()));

        return person;
    }

    private PersonDTO buildPersonFromRequest(PersonRequestDTO personRequestDTO) {
        PersonDTO person = new PersonDTO();
        person.setPersonFirstName(personRequestDTO.getPersonFirstName());
        person.setPersonLastName(personRequestDTO.getPersonLastName());
        person.setPersonCode(personRequestDTO.getPersonCode());
        person.setPersonBirthDate(personRequestDTO.getPersonBirthDate());
        person.setMedicalRiskLimitLevel(personRequestDTO.getMedicalRiskLimitLevel());
        return person;
    }

    private AgreementDTO buildAgreement(TravelCalculatePremiumRequestV3 request) {
        AgreementDTO agreement = new AgreementDTO();
        agreement.setAgreementDateFrom(request.getAgreementDateFrom());
        agreement.setAgreementDateTo(request.getAgreementDateTo());
        agreement.setCountry(request.getCountry());
        agreement.setSelectedRisks(request.getSelectedRisks());

        agreement.setPersons(buildPersonDTOFromRequest(request));

        return agreement;
    }

    private List<PersonDTO> buildPersonDTOFromRequest(TravelCalculatePremiumRequestV3 request) {
        if (request.getPersons() == null) {
            return List.of();
        } else {
            return request.getPersons().stream()
                    .map(this::buildPersonFromRequest)
                    .collect(Collectors.toList());
        }
    }

}

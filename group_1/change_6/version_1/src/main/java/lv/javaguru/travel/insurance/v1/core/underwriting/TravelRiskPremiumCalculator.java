package lv.javaguru.travel.insurance.v1.core.underwriting;

import lv.javaguru.travel.insurance.v1.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v1.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {

    BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person);

    String getRiskIc();

}

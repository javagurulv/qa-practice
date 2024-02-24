package lv.javaguru.travel.insurance.v2.core.underwriting;

import lv.javaguru.travel.insurance.v2.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v2.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {

    BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person);

    String getRiskIc();

}

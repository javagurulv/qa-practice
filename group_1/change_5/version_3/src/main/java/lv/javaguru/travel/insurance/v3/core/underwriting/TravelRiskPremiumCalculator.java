package lv.javaguru.travel.insurance.v3.core.underwriting;

import lv.javaguru.travel.insurance.v3.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.PersonDTO;

import java.math.BigDecimal;

public interface TravelRiskPremiumCalculator {

    BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person);

    String getRiskIc();

}

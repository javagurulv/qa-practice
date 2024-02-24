package lv.javaguru.travel.insurance.v3.core.underwriting;

import lv.javaguru.travel.insurance.v3.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.PersonDTO;

public interface TravelPremiumUnderwriting {

    TravelPremiumCalculationResult calculatePremium(AgreementDTO agreement, PersonDTO person);

}

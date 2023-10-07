package lv.javaguru.travel.insurance.v3.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.v3.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.v3.core.underwriting.TravelRiskPremiumCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Autowired private DayCountCalculator dayCountCalculator;
    @Autowired private CountryDefaultDayRateCalculator countryDefaultDayRateCalculator;
    @Autowired private AgeCoefficientCalculator ageCoefficientCalculator;
    @Autowired private RiskLimitLevelCalculator riskLimitLevelCalculator;

    @Override
    public BigDecimal calculatePremium(AgreementDTO agreement, PersonDTO person) {
        BigDecimal daysCount = dayCountCalculator.calculate(agreement);
        BigDecimal countryDefaultRate = countryDefaultDayRateCalculator.calculate(agreement);
        BigDecimal ageCoefficient = ageCoefficientCalculator.calculate(person);
        BigDecimal riskLimitLevel = riskLimitLevelCalculator.calculate(person);
        return countryDefaultRate
                .multiply(daysCount)
                .multiply(ageCoefficient)
                .multiply(riskLimitLevel)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}

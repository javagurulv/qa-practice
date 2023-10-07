package lv.javaguru.travel.insurance.v1.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.v1.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v1.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class DayCountCalculator {

    @Autowired private DateTimeUtil dateTimeUtil;

    BigDecimal calculate(AgreementDTO agreement) {
        Long daysBetween = dateTimeUtil.getDaysBetween(agreement.getAgreementDateFrom(), agreement.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }

}

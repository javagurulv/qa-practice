package lv.javaguru.travel.insurance.v2.core.underwriting.calculators.medical;

import lv.javaguru.travel.insurance.v2.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v2.core.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class DayCountCalculator {

    @Autowired private DateTimeUtil dateTimeUtil;

    BigDecimal calculate(AgreementDTO agreement) {
        long daysBetween = dateTimeUtil.getDaysBetween(agreement.getAgreementDateFrom(), agreement.getAgreementDateTo());

        if (daysBetween > 30) {
            daysBetween = 30;
        }

        return new BigDecimal(daysBetween);
    }

}

package lv.javaguru.travel.insurance.v3.core.underwriting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lv.javaguru.travel.insurance.v3.core.api.dto.RiskDTO;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class TravelPremiumCalculationResult {

    private BigDecimal totalPremium;

    private List<RiskDTO> risks;

}

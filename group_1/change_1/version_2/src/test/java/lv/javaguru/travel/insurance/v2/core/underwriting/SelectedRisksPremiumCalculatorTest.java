package lv.javaguru.travel.insurance.v2.core.underwriting;

import lv.javaguru.travel.insurance.v2.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v2.core.api.dto.PersonDTO;
import lv.javaguru.travel.insurance.v2.core.api.dto.RiskDTO;
import lv.javaguru.travel.insurance.v2.core.underwriting.SelectedRisksPremiumCalculator;
import lv.javaguru.travel.insurance.v2.core.underwriting.TravelRiskPremiumCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SelectedRisksPremiumCalculatorTest {

    @InjectMocks
    private SelectedRisksPremiumCalculator calculator;

    private TravelRiskPremiumCalculator riskPremiumCalculator1;
    private TravelRiskPremiumCalculator riskPremiumCalculator2;

    @BeforeEach
    public void init() {
        riskPremiumCalculator1 = mock(TravelRiskPremiumCalculator.class);
        riskPremiumCalculator2 = mock(TravelRiskPremiumCalculator.class);
        List<TravelRiskPremiumCalculator> riskPremiumCalculators = new ArrayList<>();
        riskPremiumCalculators.add(riskPremiumCalculator1);
        riskPremiumCalculators.add(riskPremiumCalculator2);
        ReflectionTestUtils.setField(calculator, "riskPremiumCalculators", riskPremiumCalculators);
    }

    @Test
    void shouldCalculatePremiumForOneRisk() {
        when(riskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculator1.calculatePremium(any(), any())).thenReturn(BigDecimal.ONE);
        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(agreement.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        List<RiskDTO> riskPremiums = calculator.calculatePremiumForAllRisks(agreement, person);
        assertEquals(riskPremiums.size(), 1);
        assertEquals(riskPremiums.get(0).getRiskIc(), "TRAVEL_MEDICAL");
        assertEquals(riskPremiums.get(0).getPremium(), BigDecimal.ONE);
    }

    @Test
    void shouldCalculatePremiumForTwoRisks() {
        when(riskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculator2.getRiskIc()).thenReturn("TRAVEL_EVACUATION");

        when(riskPremiumCalculator1.calculatePremium(any(), any())).thenReturn(BigDecimal.ONE);
        when(riskPremiumCalculator2.calculatePremium(any(), any())).thenReturn(BigDecimal.ONE);

        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);
        when(agreement.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_EVACUATION"));
        List<RiskDTO> riskPremiums = calculator.calculatePremiumForAllRisks(agreement, person);
        assertEquals(riskPremiums.size(), 2);
        assertEquals(riskPremiums.get(0).getRiskIc(), "TRAVEL_MEDICAL");
        assertEquals(riskPremiums.get(0).getPremium(), BigDecimal.ONE);
        assertEquals(riskPremiums.get(1).getRiskIc(), "TRAVEL_EVACUATION");
        assertEquals(riskPremiums.get(1).getPremium(), BigDecimal.ONE);
    }

    @Test
    void shouldThrowExceptionWhenSelectedRiskTypeNotSupported() {
        when(riskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculator2.getRiskIc()).thenReturn("TRAVEL_EVACUATION");

        AgreementDTO agreement = mock(AgreementDTO.class);
        PersonDTO person = mock(PersonDTO.class);

        when(agreement.getSelectedRisks()).thenReturn(List.of("NOT_SUPPORTED_RISK_TYPE"));

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> calculator.calculatePremiumForAllRisks(agreement, person));
        assertEquals(exception.getMessage(), "Not supported riskIc = NOT_SUPPORTED_RISK_TYPE");
    }

}
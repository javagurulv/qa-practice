package lv.javaguru.travel.insurance.v3.core.validations;

import lv.javaguru.travel.insurance.v3.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v3.core.api.dto.ValidationErrorDTO;
import lv.javaguru.travel.insurance.v3.core.validations.TravelAgreementFieldValidator;
import lv.javaguru.travel.insurance.v3.core.validations.TravelAgreementValidatorImpl;
import lv.javaguru.travel.insurance.v3.core.validations.TravelPersonFieldValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelAgreementValidatorImplTest {

    @Mock private TravelAgreementFieldValidator agreementFieldValidator;
    @Mock private TravelPersonFieldValidator personFieldValidator;

    @InjectMocks
    private TravelAgreementValidatorImpl validator;

    @Test
    public void shouldNotReturnErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreementFieldValidator.validate(agreement)).thenReturn(List.of());
        when(personFieldValidator.validate(any())).thenReturn(List.of());
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnAgreementErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreementFieldValidator.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO()));
        when(personFieldValidator.validate(any())).thenReturn(List.of());
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 1);
    }

    @Test
    public void shouldReturnPersonErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreementFieldValidator.validate(agreement)).thenReturn(List.of());
        when(personFieldValidator.validate(any())).thenReturn(List.of(new ValidationErrorDTO()));
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 1);
    }

    @Test
    public void shouldReturnAgreementAndPersonErrors() {
        AgreementDTO agreement = mock(AgreementDTO.class);
        when(agreementFieldValidator.validate(agreement)).thenReturn(List.of(new ValidationErrorDTO()));
        when(personFieldValidator.validate(any())).thenReturn(List.of(new ValidationErrorDTO()));
        List<ValidationErrorDTO> errors = validator.validate(agreement);
        assertEquals(errors.size(), 2);
    }

}
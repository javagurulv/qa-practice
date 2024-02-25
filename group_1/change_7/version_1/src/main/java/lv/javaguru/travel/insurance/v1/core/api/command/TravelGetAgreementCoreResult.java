package lv.javaguru.travel.insurance.v1.core.api.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.v1.core.api.dto.AgreementDTO;
import lv.javaguru.travel.insurance.v1.core.api.dto.ValidationErrorDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelGetAgreementCoreResult {

    private List<ValidationErrorDTO> errors;

    private AgreementDTO agreement;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    public TravelGetAgreementCoreResult(List<ValidationErrorDTO> errors) {
        this.errors = errors;
    }

}

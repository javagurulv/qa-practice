package lv.javaguru.travel.insurance.v3.dto.v3;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.v3.dto.CoreResponse;
import lv.javaguru.travel.insurance.v3.dto.ValidationError;
import lv.javaguru.travel.insurance.v3.dto.util.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponseV3 extends CoreResponse {

    @JsonFormat(pattern="yyyy-MM-dd")
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2021-01-23")
    private Date agreementDateFrom;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2021-01-23")
    private Date agreementDateTo;

    private String country;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;

    @JsonAlias("persons")
    private List<PersonResponseDTO> persons;

    public TravelCalculatePremiumResponseV3(List<ValidationError> errors) {
        super(errors);
    }

}

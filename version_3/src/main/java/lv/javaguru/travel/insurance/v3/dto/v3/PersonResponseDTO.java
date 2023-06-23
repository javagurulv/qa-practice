package lv.javaguru.travel.insurance.v3.dto.v3;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.travel.insurance.v3.dto.RiskPremium;
import lv.javaguru.travel.insurance.v3.dto.util.BigDecimalSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponseDTO {

    private String personFirstName;
    private String personLastName;
    private String personCode;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2021-01-23")
    private Date personBirthDate;

    private String medicalRiskLimitLevel;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal personPremium;

    @JsonAlias("person_risks")
    private List<RiskPremium> personRisks;

}

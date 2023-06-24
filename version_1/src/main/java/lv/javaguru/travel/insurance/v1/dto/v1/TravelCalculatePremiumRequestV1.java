package lv.javaguru.travel.insurance.v1.dto.v1;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequestV1 {

    @Schema(type = "string", required = true, example = "Vasja")
    private String personFirstName;

    @Schema(type = "string", required = true, example = "Pupkin")
    private String personLastName;

    @Schema(type = "string", required = true, example = "1234456-12345")
    private String personCode;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2021-01-23")
    private Date personBirthDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2021-01-23")
    private Date agreementDateFrom;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2021-01-23")
    private Date agreementDateTo;

    @Schema(type = "string", required = true, example = "SPAIN")
    private String country;

    @Schema(type = "string", required = true, example = "LEVEL_10000")
    private String medicalRiskLimitLevel;

    @JsonAlias("selected_risks")
    @Schema(required = true, example = "[\"TRAVEL_MEDICAL\"]")
    private List<String> selectedRisks;

}

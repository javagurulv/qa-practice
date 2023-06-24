package lv.javaguru.travel.insurance.v3.dto.v3;

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
public class TravelCalculatePremiumRequestV3 {

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2021-01-23", required = true)
    private Date agreementDateFrom;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2021-01-23", required = true)
    private Date agreementDateTo;

    @Schema(type = "string", required = true, example = "SPAIN")
    private String country;

    @JsonAlias("selected_risks")
    @Schema(required = true, example = "[\"TRAVEL_MEDICAL\"]")
    private List<String> selectedRisks;

    @JsonAlias("persons")
    @Schema(required = true)
    private List<PersonRequestDTO> persons;

}

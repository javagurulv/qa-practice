package lv.javaguru.travel.insurance.v3.dto.v3;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequestDTO {

    @Schema(type = "string", required = true, example = "Vasja")
    private String personFirstName;

    @Schema(type = "string", required = true, example = "Pupkin")
    private String personLastName;

    @Schema(type = "string", required = true, example = "1234456-12345")
    private String personCode;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2021-01-23", required = true)
    private Date personBirthDate;

    @Schema(type = "string", required = true, example = "LEVEL_10000")
    private String medicalRiskLimitLevel;

}

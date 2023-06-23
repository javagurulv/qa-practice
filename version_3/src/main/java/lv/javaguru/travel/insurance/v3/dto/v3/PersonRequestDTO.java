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

    private String personFirstName;
    private String personLastName;
    private String personCode;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", format = "date", pattern = "yyyy-MM-dd", example = "2021-01-23")
    private Date personBirthDate;

    private String medicalRiskLimitLevel;

}

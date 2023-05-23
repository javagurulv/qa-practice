package lv.javaguru.travel.insurance.v3.dto.v3;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Date personBirthDate;

    private String medicalRiskLimitLevel;

}

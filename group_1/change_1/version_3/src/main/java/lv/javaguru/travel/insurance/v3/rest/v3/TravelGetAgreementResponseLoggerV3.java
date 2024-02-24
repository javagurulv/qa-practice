package lv.javaguru.travel.insurance.v3.rest.v3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.v3.dto.v3.TravelGetAgreementResponseV3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelGetAgreementResponseLoggerV3 {

    private static final Logger logger = LoggerFactory.getLogger(TravelGetAgreementResponseLoggerV3.class);

    void log(TravelGetAgreementResponseV3 response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(response);
            logger.info("RESPONSE: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert response to JSON", e);
        }
    }

}

package lv.javaguru.travel.insurance.v1.rest.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.javaguru.travel.insurance.v1.dto.v1.TravelGetAgreementResponseV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelGetAgreementResponseLoggerV1 {

    private static final Logger logger = LoggerFactory.getLogger(TravelGetAgreementResponseLoggerV1.class);

    void log(TravelGetAgreementResponseV1 response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(response);
            logger.info("RESPONSE: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert response to JSON", e);
        }
    }

}

package lv.javaguru.travel.insurance.v1.rest.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelGetAgreementRequestLoggerV1 {

    private static final Logger logger = LoggerFactory.getLogger(TravelGetAgreementRequestLoggerV1.class);

    void log(Long agreementId) {
        logger.info("REQUEST: agreementId = " + agreementId);
    }

}

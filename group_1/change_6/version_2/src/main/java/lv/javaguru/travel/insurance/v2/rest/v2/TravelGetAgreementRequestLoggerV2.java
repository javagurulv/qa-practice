package lv.javaguru.travel.insurance.v2.rest.v2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class TravelGetAgreementRequestLoggerV2 {

    private static final Logger logger = LoggerFactory.getLogger(TravelGetAgreementRequestLoggerV2.class);

    void log(Long agreementId) {
        logger.info("REQUEST: agreementId = " + agreementId);
    }

}

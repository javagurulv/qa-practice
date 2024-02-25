package lv.javaguru.travel.insurance.v2.core.services;

import lv.javaguru.travel.insurance.v2.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.v2.core.api.command.TravelGetAgreementCoreResult;

public interface TravelGetAgreementService {

    TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command);

}

package lv.javaguru.travel.insurance.v3.core.services;

import lv.javaguru.travel.insurance.v3.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.v3.core.api.command.TravelGetAgreementCoreResult;

public interface TravelGetAgreementService {

    TravelGetAgreementCoreResult getAgreement(TravelGetAgreementCoreCommand command);

}

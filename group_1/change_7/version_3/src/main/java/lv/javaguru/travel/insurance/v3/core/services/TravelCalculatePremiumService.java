package lv.javaguru.travel.insurance.v3.core.services;

import lv.javaguru.travel.insurance.v3.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.v3.core.api.command.TravelCalculatePremiumCoreResult;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumCoreResult calculatePremium(TravelCalculatePremiumCoreCommand command);

}

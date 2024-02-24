package lv.javaguru.travel.insurance.v3.rest.v3;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.v3.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.v3.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.v3.core.services.TravelGetAgreementService;
import lv.javaguru.travel.insurance.v3.dto.v3.DtoV3Converter;
import lv.javaguru.travel.insurance.v3.dto.v3.TravelGetAgreementResponseV3;
import lv.javaguru.travel.insurance.v3.rest.common.RestRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v3/agreement")
public class TravelGetAgreementRestControllerV3 {

	@Autowired private TravelGetAgreementRequestLoggerV2 requestLogger;
	@Autowired private TravelGetAgreementResponseLoggerV3 responseLogger;
	@Autowired private RestRequestExecutionTimeLogger executionTimeLogger;
	@Autowired private TravelGetAgreementService getAgreementService;
	@Autowired private DtoV3Converter dtoV3Converter;

	@GetMapping(path = "/{agreementId}", produces = "application/json")
	public TravelGetAgreementResponseV3 getAgreement(@PathVariable Long agreementId) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelGetAgreementResponseV3 response = processRequest(agreementId);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelGetAgreementResponseV3 processRequest(Long agreementId) {
		requestLogger.log(agreementId);
		TravelGetAgreementCoreCommand coreCommand = dtoV3Converter.buildGetAgreementCoreCommand(agreementId);
		TravelGetAgreementCoreResult coreResult = getAgreementService.getAgreement(coreCommand);
		TravelGetAgreementResponseV3 response = dtoV3Converter.buildGetAgreementResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}
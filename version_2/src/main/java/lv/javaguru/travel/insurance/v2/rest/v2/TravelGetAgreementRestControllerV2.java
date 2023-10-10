package lv.javaguru.travel.insurance.v2.rest.v2;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.v2.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.v2.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.v2.core.services.TravelGetAgreementService;
import lv.javaguru.travel.insurance.v2.dto.v2.DtoV2Converter;
import lv.javaguru.travel.insurance.v2.dto.v2.TravelGetAgreementResponseV2;
import lv.javaguru.travel.insurance.v2.rest.common.RestRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v1/agreement")
public class TravelGetAgreementRestControllerV2 {

	@Autowired private TravelGetAgreementRequestLoggerV2 requestLogger;
	@Autowired private TravelGetAgreementResponseLoggerV2 responseLogger;
	@Autowired private RestRequestExecutionTimeLogger executionTimeLogger;
	@Autowired private TravelGetAgreementService getAgreementService;
	@Autowired private DtoV2Converter dtoV2Converter;

	@GetMapping(path = "/{agreementId}", produces = "application/json")
	public TravelGetAgreementResponseV2 getAgreement(@PathVariable Long agreementId) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelGetAgreementResponseV2 response = processRequest(agreementId);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelGetAgreementResponseV2 processRequest(Long agreementId) {
		requestLogger.log(agreementId);
		TravelGetAgreementCoreCommand coreCommand = dtoV2Converter.buildGetAgreementCoreCommand(agreementId);
		TravelGetAgreementCoreResult coreResult = getAgreementService.getAgreement(coreCommand);
		TravelGetAgreementResponseV2 response = dtoV2Converter.buildGetAgreementResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}
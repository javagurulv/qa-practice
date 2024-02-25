package lv.javaguru.travel.insurance.v1.rest.v1;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.v1.core.api.command.TravelGetAgreementCoreCommand;
import lv.javaguru.travel.insurance.v1.core.api.command.TravelGetAgreementCoreResult;
import lv.javaguru.travel.insurance.v1.core.services.TravelGetAgreementService;
import lv.javaguru.travel.insurance.v1.dto.v1.DtoV1Converter;
import lv.javaguru.travel.insurance.v1.dto.v1.TravelGetAgreementResponseV1;
import lv.javaguru.travel.insurance.v1.rest.common.RestRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/insurance/travel/api/v1/agreement")
public class TravelGetAgreementRestControllerV1 {

	@Autowired private TravelGetAgreementRequestLoggerV1 requestLogger;
	@Autowired private TravelGetAgreementResponseLoggerV1 responseLogger;
	@Autowired private RestRequestExecutionTimeLogger executionTimeLogger;
	@Autowired private TravelGetAgreementService getAgreementService;
	@Autowired private DtoV1Converter dtoV1Converter;

	@GetMapping(path = "/{agreementId}", produces = "application/json")
	public TravelGetAgreementResponseV1 getAgreement(@PathVariable Long agreementId) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelGetAgreementResponseV1 response = processRequest(agreementId);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelGetAgreementResponseV1 processRequest(Long agreementId) {
		requestLogger.log(agreementId);
		TravelGetAgreementCoreCommand coreCommand = dtoV1Converter.buildGetAgreementCoreCommand(agreementId);
		TravelGetAgreementCoreResult coreResult = getAgreementService.getAgreement(coreCommand);
		TravelGetAgreementResponseV1 response = dtoV1Converter.buildGetAgreementResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}
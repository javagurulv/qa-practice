package lv.javaguru.travel.insurance.v2.rest.v2;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.v2.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.v2.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.v2.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.v2.dto.v1.DtoV1Converter;
import lv.javaguru.travel.insurance.v2.dto.v1.TravelCalculatePremiumRequestV1;
import lv.javaguru.travel.insurance.v2.dto.v1.TravelCalculatePremiumResponseV1;
import lv.javaguru.travel.insurance.v2.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v2")
public class TravelCalculatePremiumRestControllerV2 {

	@Autowired private TravelCalculatePremiumRequestLoggerV2 requestLogger;
	@Autowired private TravelCalculatePremiumResponseLoggerV2 responseLogger;
	@Autowired private TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger;
	@Autowired private TravelCalculatePremiumService calculatePremiumService;
	@Autowired private DtoV1Converter dtoV1Converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV1 calculatePremium(@RequestBody TravelCalculatePremiumRequestV1 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV1 response = processRequest(request);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelCalculatePremiumResponseV1 processRequest(TravelCalculatePremiumRequestV1 request) {
		requestLogger.log(request);
		TravelCalculatePremiumCoreCommand coreCommand = dtoV1Converter.buildCoreCommand(request);
		TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
		TravelCalculatePremiumResponseV1 response = dtoV1Converter.buildResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}
package lv.javaguru.travel.insurance.v2.rest.v2;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.v2.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.v2.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.v2.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.v2.dto.v2.DtoV2Converter;
import lv.javaguru.travel.insurance.v2.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.v2.dto.v2.TravelCalculatePremiumResponseV2;
import lv.javaguru.travel.insurance.v2.rest.common.RestRequestExecutionTimeLogger;
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
	@Autowired private RestRequestExecutionTimeLogger executionTimeLogger;
	@Autowired private TravelCalculatePremiumService calculatePremiumService;
	@Autowired private DtoV2Converter dtoV2Converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV2 calculatePremium(@RequestBody TravelCalculatePremiumRequestV2 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV2 response = processRequest(request);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelCalculatePremiumResponseV2 processRequest(TravelCalculatePremiumRequestV2 request) {
		requestLogger.log(request);
		TravelCalculatePremiumCoreCommand coreCommand = dtoV2Converter.buildCalculatePremiumCoreCommand(request);
		TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
		TravelCalculatePremiumResponseV2 response = dtoV2Converter.buildCalculatePremiumResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}
package lv.javaguru.travel.insurance.v3.rest.v3;

import com.google.common.base.Stopwatch;
import lv.javaguru.travel.insurance.v3.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.v3.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.v3.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.v3.dto.v3.DtoV3Converter;
import lv.javaguru.travel.insurance.v3.dto.v3.TravelCalculatePremiumRequestV3;
import lv.javaguru.travel.insurance.v3.dto.v3.TravelCalculatePremiumResponseV3;
import lv.javaguru.travel.insurance.v3.rest.common.TravelCalculatePremiumRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel/api/v3")
public class TravelCalculatePremiumRestControllerV3 {

	@Autowired private TravelCalculatePremiumRequestLoggerV3 requestLogger;
	@Autowired private TravelCalculatePremiumResponseLoggerV3 responseLogger;
	@Autowired private TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger;
	@Autowired private TravelCalculatePremiumService calculatePremiumService;
	@Autowired private DtoV3Converter dtoV3Converter;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponseV3 calculatePremium(@RequestBody TravelCalculatePremiumRequestV3 request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponseV3 response = processRequest(request);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelCalculatePremiumResponseV3 processRequest(TravelCalculatePremiumRequestV3 request) {
		requestLogger.log(request);
		TravelCalculatePremiumCoreCommand coreCommand = dtoV3Converter.buildCoreCommand(request);
		TravelCalculatePremiumCoreResult coreResult = calculatePremiumService.calculatePremium(coreCommand);
		TravelCalculatePremiumResponseV3 response = dtoV3Converter.buildResponse(coreResult);
		responseLogger.log(response);
		return response;
	}

}
package lv.javaguru.travel.insurance.v3.web.v3;

import lv.javaguru.travel.insurance.v3.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.v3.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.v3.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.v3.dto.v3.DtoV3Converter;
import lv.javaguru.travel.insurance.v3.dto.v3.TravelCalculatePremiumRequestV3;
import lv.javaguru.travel.insurance.v3.dto.v3.TravelCalculatePremiumResponseV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TravelInsuranceControllerV3 {

    @Autowired private TravelCalculatePremiumService service;
    @Autowired private DtoV3Converter dtoV3Converter;

    @GetMapping("/insurance/travel/web/v3")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV3());
        modelMap.addAttribute("response", new TravelCalculatePremiumResponseV3());
        return "travel-calculate-premium-v3";
    }

    @PostMapping("/insurance/travel/web/v3")
    public String processForm(@ModelAttribute(value = "request") TravelCalculatePremiumRequestV3 request,
                              ModelMap modelMap) {
        TravelCalculatePremiumCoreCommand coreCommand = dtoV3Converter.buildCoreCommand(request);
        TravelCalculatePremiumCoreResult coreResult = service.calculatePremium(coreCommand);
        TravelCalculatePremiumResponseV3 response = dtoV3Converter.buildResponse(coreResult);
        modelMap.addAttribute("response", response);
        return "travel-calculate-premium-v3";
    }

}

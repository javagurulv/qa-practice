package lv.javaguru.travel.insurance.v2.web.v2;

import lv.javaguru.travel.insurance.v2.core.api.command.TravelCalculatePremiumCoreCommand;
import lv.javaguru.travel.insurance.v2.core.api.command.TravelCalculatePremiumCoreResult;
import lv.javaguru.travel.insurance.v2.core.services.TravelCalculatePremiumService;
import lv.javaguru.travel.insurance.v2.dto.v2.DtoV2Converter;
import lv.javaguru.travel.insurance.v2.dto.v2.TravelCalculatePremiumRequestV2;
import lv.javaguru.travel.insurance.v2.dto.v2.TravelCalculatePremiumResponseV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TravelInsuranceControllerV2 {

    @Autowired private TravelCalculatePremiumService service;
    @Autowired private DtoV2Converter dtoV2Converter;

    @GetMapping("/insurance/travel/web/v2")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new TravelCalculatePremiumRequestV2());
        modelMap.addAttribute("response", new TravelCalculatePremiumResponseV2());
        return "travel-calculate-premium-v2";
    }

    @PostMapping("/insurance/travel/web/v2")
    public String processForm(@ModelAttribute(value = "request") TravelCalculatePremiumRequestV2 request,
                              ModelMap modelMap) {
        TravelCalculatePremiumCoreCommand coreCommand = dtoV2Converter.buildCalculatePremiumCoreCommand(request);
        TravelCalculatePremiumCoreResult coreResult = service.calculatePremium(coreCommand);
        TravelCalculatePremiumResponseV2 response = dtoV2Converter.buildCalculatePremiumResponse(coreResult);
        modelMap.addAttribute("response", response);
        return "travel-calculate-premium-v2";
    }

}

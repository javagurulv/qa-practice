package lv.javaguru.travel.insurance.v2.rest.v2;

import org.junit.jupiter.api.Test;

public class TravelCalculatePremiumControllerTestCase20 extends TravelCalculatePremiumControllerV2TestCase {

    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "test_case_20";
    }
}
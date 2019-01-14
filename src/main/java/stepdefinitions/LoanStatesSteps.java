package stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import http.HTTPRequest;
import http.HTTPResponse;
import json.JsonValidator;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanStatesSteps {

    String URL = "";
    HTTPResponse response = new HTTPResponse();
    SoftAssert verify = new SoftAssert();

    @Given("^User wants to recover the elegible states from API \"([^\"]*)\"$")
    public void userWantsToRecoverTheElegibleStatesFromAPI(String url) {
        URL = url;
    }

    @When("^User performs the call to the API$")
    public void userPerformsTheCallToTheAPI() throws Throwable {
        HTTPRequest request = new HTTPRequest();
        response = request.getHTMLCode(URL);
    }

    @Then("^The result code is (\\d+)$")
    public void theResultCodeIs(int responsecode) throws Throwable {
        verify.assertEquals(response.getResponseCode(), (Integer)responsecode);
    }

    @And("^All the state names returned are valid$")
    public void allTheStateNamesReturnedAreValid() {
        List<String> states = new ArrayList<>();
        JsonValidator jsonval = new JsonValidator();
        states = jsonval.getElements(response.getResponseBody(), "states");
        for(String state : states){
            verify.assertTrue(jsonval.validateJson(state, "state.json"));
        }
    }

    @And("^Total state count is (\\d+)$")
    public void totalStateCountIs(int count) {
        List<String> states = new ArrayList<>();
        JsonValidator jsonval = new JsonValidator();
        states = jsonval.getElements(response.getResponseBody(), "states");
        verify.assertEquals(states.size(), count);
    }

    @And("^Only one state has a min age (\\d+)$")
    public void onlyOneStateHasAMinAge(int minage) {
        List<String> states = new ArrayList<>();
        Map<Integer, Integer> ages = new HashMap<>();
        JsonValidator jsonval = new JsonValidator();
        states = jsonval.getElements(response.getResponseBody(), "states");
        for(String state : states){
            Integer age = jsonval.getIntegerField(state, "minAge");
            if(ages.containsKey(age))
                ages.put(age, ages.get(age) + 1);
            else
                ages.put(age, 1);
        }

        verify.assertEquals(ages.get(minage), (Integer)1);
    }

    @And("^\"([^\"]*)\" is the only state with min loan amount of (\\d+)$")
    public void isTheOnlyStateWithMinLoanAmountOf(String statename, int minamount) {
        JsonValidator jsonval = new JsonValidator();
        String element = jsonval.getElement(response.getResponseBody(), "states", "label", statename);
        Integer amount = jsonval.getIntegerField(element,"minLoanAmount");
        verify.assertEquals(amount, (Integer) minamount);
    }

    @After
    public void tearDown() {
        verify.assertAll();
    }
}

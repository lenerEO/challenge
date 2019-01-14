package stepdefinitions;

import com.github.javafaker.Faker;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import objects.Loan;
import objects.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;
import pageobjects.CheckYourRatePage;
import pageobjects.LoginPage;
import pageobjects.SelectOfferPage;
import pageobjects.PersonalLoansPage;

import java.text.SimpleDateFormat;

public class LoanOfferSteps {

    public static WebDriver driver;

    User user = new User();
    Loan loan = new Loan();

    /*@Before
    public void startUp() {
        driver = new ChromeDriver();
    }*/

    @Given("^User is in main page$")
    public void userIsInMainPage() {
        driver = new ChromeDriver();
        driver.get("https://www.credify.tech/");
    }

    @When("^User checks his rate with parameters: \"([^\"]*)\" and \"([^\"]*)\"$")
    public void userChecksHisRateWithParametersAnd(String loanamount, String loanpurpose) {
        PersonalLoansPage personal = new PersonalLoansPage(driver);
        personal.setLoanAmount(loanamount);
        personal.setLoanPurpose(loanpurpose);
        personal.clickCheckRate();
    }

    @After
    public void tearDown() {
        if(driver != null)
            driver.quit();
    }

    @And("^User fills out random basic information$")
    public void userFillsOutRandomBasicInformation() {
        CheckYourRatePage check = new CheckYourRatePage(driver);
        Faker faker = new Faker();

        check.setFirstName(faker.name().firstName());
        check.setLastName(faker.name().lastName());
        check.setAddress("Luisa Lane");
        check.setCity("Alvarado");
        check.setState("TX");
        check.setZipCode("76009");
        check.setDOB(new SimpleDateFormat("MM/dd/yyyy").format(faker.date().birthday(40, 60)));
    }

    @And("^User fills out important information: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void userFillsOutImportantInformation(String income, String additionalincome, String mail, String password) {
        CheckYourRatePage check = new CheckYourRatePage(driver);
        Faker faker = new Faker();

        check.setIncome(income);
        check.setAdditionalIncome(additionalincome);

        user.setUsername(mail + "+" + faker.random().nextInt(1,100) + "@upgrade-challenge.com");
        check.setUsername(user.getUsername());
        user.setPassword(password);
        check.setPassword(user.getPassword());
    }

    @And("^User checks Terms and submits the form$")
    public void userChecksTermsAndSubmitsTheForm() {
        CheckYourRatePage check = new CheckYourRatePage(driver);

        check.checkTerms();
        check.clickSubmit();
    }

    @Then("^User can see the offer$")
    public void userCanSeeTheOffer() {
        SelectOfferPage offer = new SelectOfferPage(driver);
        loan.setLoanAmount(offer.getLoanAmount());
        loan.setAPR(offer.getAPR());
        loan.setMonthlyPayment(offer.getMonthlyPayment());
        loan.setLoanTerm(offer.getLoanTerm());
    }

    @When("^User reenter to the offer$")
    public void userReenterToTheOffer() {
        SelectOfferPage line = new SelectOfferPage(driver);
        line.clickMenu();
        line.clickSignOut();

        driver.get("https://www.credify.tech/");

        PersonalLoansPage personal = new PersonalLoansPage(driver);
        personal.clickSignIn();

        LoginPage login = new LoginPage(driver);
        login.setUsername(user.getUsername());
        login.setPassword(user.getPassword());
        login.clickLogin();
    }

    @Then("^User can see the same offer$")
    public void userCanSeeTheSameOffer() {
        SelectOfferPage offer = new SelectOfferPage(driver);
        SoftAssert verify = new SoftAssert();
        verify.assertEquals(offer.getLoanAmount(), loan.getLoanAmount());
        verify.assertEquals(offer.getAPR(), loan.getAPR());
        verify.assertEquals(offer.getMonthlyPayment(), loan.getMonthlyPayment());
        verify.assertEquals(offer.getLoanTerm(), loan.getLoanTerm());
        verify.assertAll();
        offer.clickMenu();
        offer.clickSignOut();
    }
}

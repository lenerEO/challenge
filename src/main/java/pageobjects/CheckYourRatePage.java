package pageobjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckYourRatePage extends BasePage{
    WebDriver driver;

    public CheckYourRatePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='borrowerFirstName']")
    private WebElement txtFirstName;

    @FindBy(css = "input[name='borrowerLastName']")
    private WebElement txtLastName;

    @FindBy(css = "input[name='borrowerStreet']")
    private WebElement txtAddress;

    @FindBy(css = "input[name='borrowerCity']")
    private WebElement txtCity;

    @FindBy(css = "input[name='borrowerState']")
    private WebElement txtState;

    @FindBy(css = "input[name='borrowerZipCode']")
    private WebElement txtZipCode;

    @FindBy(css = "input[name='borrowerDateOfBirth']")
    private WebElement txtDOB;

    @FindBy(css = "input[name='borrowerIncome']")
    private WebElement txtIncome;

    @FindBy(css = "input[name='borrowerAdditionalIncome']")
    private WebElement txtAdditionalIncome;

    @FindBy(css = "input[name='username']")
    private WebElement txtUsername;

    @FindBy(css = "input[name='password']")
    private WebElement txtPassword;

    @FindBy(css = "input[name='agreements'] + div")
    private WebElement chkTerms;

    @FindBy(css = "button[data-auto='submitPersonalInfo']")
    private WebElement btnSubmit;

    public void setFirstName(String firstname){
        typeTextbox(txtFirstName, firstname);
    }

    public void setLastName(String lastname){
        typeTextbox(txtLastName, lastname);
    }

    public void setAddress(String address){
        typeTextbox(txtAddress, address);
    }

    public void setCity(String city){
        typeTextbox(txtCity, city);
    }

    public void setState(String state){
        typeTextbox(txtState, state);
    }

    public void setZipCode(String zipcode){
        typeTextbox(txtZipCode, zipcode);
    }

    public void setDOB(String dob){
        typeTextbox(txtDOB, dob);
    }

    public void setIncome(String income){
        typeTextbox(txtIncome, income);
    }

    public void setAdditionalIncome(String additionalincome){
        typeTextbox(txtAdditionalIncome, additionalincome);
    }

    public void setUsername(String username){
        typeTextbox(txtUsername, username);
    }

    public void setPassword(String password){
        typeTextbox(txtPassword, password);
    }

    public void checkTerms(){
        doCheck(chkTerms);
    }

    public void uncheckTerms(){
        doUnCheck(chkTerms);
    }

    public void clickSubmit(){
        doClick(btnSubmit);
    }
}
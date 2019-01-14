package pageobjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalLoansPage extends BasePage {
    WebDriver driver;

    public PersonalLoansPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "loan-amount")
    private WebElement txtLoanAmount;

    @FindBy(id = "loan-purpose-select")
    private WebElement cmbLoanPurpose;

    @FindBy(id = "loan-form-submit")
    private WebElement btnLoanFormSubmit;

    @FindBy(css = "a[href='/portal/']")
    private WebElement mnuSignIn;

    public void setLoanAmount(String amount){
        typeTextbox(txtLoanAmount, amount);
    }

    public void setLoanPurpose(String purpose){
        doSelect(cmbLoanPurpose, purpose);
    }

    public void clickCheckRate(){
        doClick(btnLoanFormSubmit);
    }

    public void clickSignIn(){
        doClick(mnuSignIn);
    }
}
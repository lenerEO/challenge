package pageobjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectOfferPage extends BasePage {
    WebDriver driver;

    public SelectOfferPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span[data-auto='userLoanAmount']")
    private WebElement lblLoanAmount;

    @FindBy(css = "div[data-auto='defaultMoreInfoAPR'] > div")
    private WebElement lblAPR;

    @FindBy(css = "span[data-auto='defaultMonthlyPayment'")
    private WebElement lblMonthlyPayment;

    @FindBy(css = "div[data-auto='defaultLoanTerm']")
    private WebElement lblLoanTerm;

    @FindBy(css = "div.header-nav")
    private WebElement mnuMenu;

    @FindBy(css = "a[href='/phone/logout']")
    private WebElement mnuSignOut;

    public String getLoanAmount(){
        return getLabelText(lblLoanAmount);
    }

    public String getAPR(){
        return getLabelText(lblAPR);
    }

    public String getMonthlyPayment(){
        return getLabelText(lblMonthlyPayment);
    }

    public String getLoanTerm(){
        return getLabelText(lblLoanTerm);
    }

    public void clickMenu(){
        doClick(mnuMenu);
    }

    public void clickSignOut(){
        doClick(mnuSignOut);
    }
}

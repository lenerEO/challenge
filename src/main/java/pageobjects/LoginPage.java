package pageobjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[name='username']")
    private WebElement txtUasername;

    @FindBy(css = "input[name='password']")
    private WebElement txtPassword;

    @FindBy(css = "button[data-auto='login']")
    private WebElement btnLogin;

    public void setUsername(String username){
        typeTextbox(txtUasername, username);
    }

    public void setPassword(String password){
        typeTextbox(txtPassword, password);
    }

    public void clickLogin(){
        doClick(btnLogin);
    }
}

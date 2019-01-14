package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private WebDriver driver;
    private Select dropdown;
    private int defaultWaitingTime = 30;

    //CONSTRUCTOR SECTION

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //COMMON LOCATORS SECTION

    @FindBy(css = "title")
    private WebElement lblTitle;

    //COMMON METHODS SECTION

    public boolean verifyTitle(String title) {
        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);
        wait.until(ExpectedConditions.visibilityOf(lblTitle));

        return lblTitle.getText().equalsIgnoreCase(title);
    }

    public void doClick(WebElement button) {
        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);

        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    public void typeTextbox(WebElement textbox, String text) {
        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);

        wait.until(ExpectedConditions.visibilityOf(textbox));
        textbox.clear();
        textbox.sendKeys(text);
    }

    public void doSelect(WebElement select, String value) {
        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);

        wait.until(ExpectedConditions.elementToBeClickable(select));
        dropdown = new Select(select);
        dropdown.selectByVisibleText(value);
    }

    public void doCheck(WebElement checkbox) {
        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);

        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if(!checkbox.isSelected())
            checkbox.click();
    }

    public void doUnCheck(WebElement checkbox) {
        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);

        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if(checkbox.isSelected())
            checkbox.click();
    }

    public String getLabelText(WebElement label){
        WebDriverWait wait = new WebDriverWait(driver, defaultWaitingTime);

        wait.until(ExpectedConditions.visibilityOf(label));
        return label.getText();
    }
}

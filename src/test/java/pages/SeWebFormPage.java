package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.company.coreortest.web.ui.webdriver.selenium.utils.WebDriverWaitUtility;

public class SeWebFormPage {
    private WebDriverWaitUtility webDriverWaitUtility;

    public SeWebFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriverWaitUtility = new WebDriverWaitUtility(driver);
    }

    @FindBy(name = "my-text")
    @CacheLookup
    public WebElement myText;

    @FindBy(css = "button[type='submit']")
    @CacheLookup
    public WebElement button;

    public void clickSubmitButton() {
        webDriverWaitUtility.waitUntil(ExpectedConditions.visibilityOf(button), 10);
        button.click();
    }
}

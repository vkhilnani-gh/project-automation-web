package actions;

import org.openqa.selenium.WebDriver;

import com.company.coreortest.web.ui.webdriver.selenium.utils.ActionUtility;
import com.company.coreortest.web.ui.webdriver.selenium.utils.WebDriverWaitUtility;
import com.company.coreortest.web.ui.webdriver.selenium.utils.WebElementTextUtility;

import pages.SeWebFormPage;

public class SeWebFormAction extends SeWebFormPage {
    private final WebDriver driver;
    private final ActionUtility actionUtility;
    private final WebDriverWaitUtility webDriverWaitUtility;
    private final WebElementTextUtility webElementTextUtility;

    public SeWebFormAction(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.actionUtility = new ActionUtility(driver);
        this.webDriverWaitUtility = new WebDriverWaitUtility(driver);
        this.webElementTextUtility = new WebElementTextUtility(driver);
    }

//    public void enterText(String text) {
//        myText.sendKeys("test text");
//    }

    public SeWebFormAction enterText(String text) {
        myText.sendKeys("test text");
        return this;
    }

    public String getSubmitButtonText() {
        return webElementTextUtility.getElementText(button);
    }

    //Check if this can return the next page that gets displayed after clicking on submit button so that asserting on
    // it is feasible
    public void submitForm() {
        //actionUtility.hoverOnElement(button);
        actionUtility.clickElement(button);
    }
}

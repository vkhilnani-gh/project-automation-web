package com.company.coreortest.web.ui.webdriver.selenium.utils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.company.coreortest.web.ui.exception.AutomationException;

public class WebElementTextUtility {
    private WebDriver driver;
    private WebDriverWaitUtility webDriverWaitUtility;
    private WebElementMisc webElementMisc;

    public WebElementTextUtility(WebDriver driver) {
        if (driver == null) {
            throw new AutomationException("WebDriver cannot be null");
        }
        this.driver = driver;
        this.webDriverWaitUtility = new WebDriverWaitUtility(driver);
        this.webElementMisc = new WebElementMisc(driver);
    }

    public String getElementText(WebElement element) {
        String elementString = null;
        int attempts = 0;
        while (attempts < 3) {
            try {
                webDriverWaitUtility.waitUntil(ExpectedConditions.visibilityOf(element), 5);
                elementString = element.getText();
                if (elementString.isEmpty()) {
                    continue;
                }
                break;
            } catch (StaleElementReferenceException e) {
                element = webElementMisc.refreshElementLocator(element);
                attempts++;
            }
        }
        return elementString;
    }
}

package com.company.coreortest.web.ui.webdriver.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WebElementStateCheckUtility {
    private WebDriver webDriver;

    public WebElementStateCheckUtility(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public boolean elementExists(By locator) {
        return !webDriver.findElements(locator).isEmpty();
    }

    public boolean checkElementDisplayed(WebElement element) {
        WebDriverWaitUtility waitUtility = new WebDriverWaitUtility(webDriver);
        WebElementMisc loc = new WebElementMisc(webDriver);
        boolean elementStatus = false;
        int attempts = 0;
        while (attempts < 3) {
            try {
                waitUtility.waitUntil(ExpectedConditions.visibilityOf(element), 5);
                elementStatus = element.isDisplayed();
                if (Boolean.FALSE.equals(elementStatus)) {
                    continue;
                }
                break;
            } catch (Exception e) {
                element = loc.refreshElementLocator(element);
                attempts++;
            }
        }
        return elementStatus;
    }
}

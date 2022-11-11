package com.company.coreortest.web.ui.webdriver.selenium.utils;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.company.coreortest.web.ui.exception.AutomationException;

public class BrowserUtility {

    private WebDriver driver;

    public BrowserUtility(WebDriver driver) {
        if (driver == null) {
            throw new AutomationException("WebDriver cannot be null");
        }
        this.driver = driver;
    }

    /**
     * Switch to a specific tab when multiple browser(windows) tabs are open.
     * @param handle (String) - target handle
     */
    public void switchToWindowByTitle(String handle) {
        try {
            driver.switchTo().window(handle);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Window with title %s not found", handle));
        }
    }

    /**
     * Method to open a new window and then switch to it.
     */
    public void switchToNewWindow() {
        new JavaScriptExecutorUtility(driver).executeScript("window.open('');");
        //Returns parent window as String
        String parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();

        Iterator<String> i1 = s.iterator();

        while (i1.hasNext()) {
            String childWindow = i1.next();
            if (!parent.equals(childWindow)) {
                driver.switchTo().window(childWindow);
            }
        }
    }
}

package com.company.coreortest.web.ui.webdriver.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * WebDriver Factory Interface
 */

public interface WebDriverFactory {
    /**
     * Returns a WedDriver Instance
     * @param capabilities The @link BrowserCapabilities required from WebDriver
     * @return WebDriver
     */

    WebDriver getWebDriver(BrowserCapabilities capabilities);
}

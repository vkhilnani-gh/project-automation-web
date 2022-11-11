package com.company.coreortest.web.ui.webdriver.selenium.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

/**
 * A WebDriver utility class that provides methods to handle different driver wait conditions.
 */
public class WebDriverWaitUtility {
    private WebDriver driver;

    /**
     * @param driver WebDriver instance to which WebDriverWait will be applied to
     */

    public WebDriverWaitUtility(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * A generic wrapper method that applies fluent wait for a given ExpectedCondition
     *
     * @param expectedCondition it can be one of the selenium ExpectedConditions or a custom Condition
     * @param maxTimeOutSeconds
     * @param <T>
     * @return returns an instance of the type defined in the ExpectedCondition.Function Class
     *
     * e.g/
     * public static ExpectedCondition<WebElement> presenceOfElementLocated(final By Locator)
     * , will return an instance of the local WebElement
     */

    public <T> T waitUntil(Function<? super WebDriver, T> expectedCondition, int maxTimeOutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxTimeOutSeconds));
        return wait.until(expectedCondition);
    }
}

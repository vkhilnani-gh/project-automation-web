package com.company.coreortest.web.ui.webdriver.selenium.utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class JavaScriptExecutorUtility {
    private WebDriver webDriver;

    public JavaScriptExecutorUtility(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    /**
     * Method to execute Javascript
     * @param script (String) - Javascript to be executed
     * @param conditionScript (Scring) - condition script to check and wait against
     * @param timeoutInSeconds
     * @return value of script being executed (i.e Boolean, Long, Double, String, List, Map, Webelement or null)
     */
    public Object executeScriptWithMaxTimeOut(String script, String conditionScript, int timeoutInSeconds) {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
        wait.until((Function<WebDriver, Boolean>) d -> ((JavascriptExecutor) d).executeScript(conditionScript) == Boolean.TRUE);
        return jse.executeScript(script);
    }

    /**
     * Method to execute Javascript
     * @param script (String) - Javascript to be executed
     * @return value of script being executed (i.e Boolean, Long, Double, String, List, Map, Webelement or null)
     */
    public Object executeScript(String script, Object... args) {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        return jse.executeScript(script, args);
    }
}

package com.company.coreortest.web.ui.webdriver.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import com.company.coreortest.web.ui.exception.AutomationException;
import com.company.coreortest.web.ui.utils.ConfigRetriever;

/**
 * Default WebDriver provider Factory
 */
public class WebDriverFactoryDefault
        implements WebDriverFactory {
    //private WebDriver driver;

    @Override
    public WebDriver getWebDriver(BrowserCapabilities browserCapabilities) {
        String platformToRun = ConfigRetriever.getInstance().getProperty(CapabilityConstant.PLATFORM);
        if (StringUtils.isNotBlank(platformToRun) && platformToRun.contains(CapabilityConstant.REMOTE_DRIVER_BROWSERSTACK)) {
            try {
                return new RemoteWebDriverProviderBS(browserCapabilities).createRemoteWebDriverBS();
            } catch (MalformedURLException e) {
                throw new AutomationException("Remote Web Driver instance for Browser Stack could not be initiated.");
            }
        } else if (StringUtils.isNotBlank(platformToRun) && platformToRun.contains(CapabilityConstant.REMOTE_DRIVER_DOCKER)) {
            try {
                return new RemoteWebDriverProviderDocker(browserCapabilities).createRemoteWebDriverDocker();
            } catch (MalformedURLException e) {
                throw new AutomationException("Remote Web Driver instance for Docker could not be initiated.");
            }
        } else {
            return new LocalWebDriverProvider(browserCapabilities).createLocalWebDriver();
        }
    }
}
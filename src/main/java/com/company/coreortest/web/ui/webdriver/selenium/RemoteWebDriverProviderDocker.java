package com.company.coreortest.web.ui.webdriver.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.company.coreortest.web.ui.exception.AutomationException;
import com.company.coreortest.web.ui.utils.ConfigRetriever;

public class RemoteWebDriverProviderDocker {
    private BrowserCapabilities browserCapabilities;

    public RemoteWebDriverProviderDocker(BrowserCapabilities browserCapabilities) {
        this.browserCapabilities = browserCapabilities;
    }

    public WebDriver createRemoteWebDriverDocker() throws MalformedURLException {
        String browserName = ConfigRetriever.getInstance().getProperty(CapabilityConstant.BROWSER_NAME);
        String driverURL = "http://localhost:4444/wd/hub";
        if (StringUtils.isBlank(CapabilityConstant.BROWSER_NAME)) {
            throw new AutomationException("Browser Name property " + CapabilityConstant.BROWSER_NAME + " is not set. " +
                    "Ensure that it is specified in one of the following - system property, env variable or .properties file");
        } else {
            WebDriver driver;
            Map<String, Object> capabilityMap;
            switch (browserName) {
                case (BrowserType.CHROME):
                    ChromeOptions chromeOptions = browserCapabilities.getChromeOptions();
//                    chromeOptions = new ChromeOptions();
//                    chromeOptions.setAcceptInsecureCerts(true);
//                    chromeOptions.setCapability("resolution", "1920X1080");
                    driver = new RemoteWebDriver(new URL(driverURL), chromeOptions);
                    break;

                case (BrowserType.FIREFOX):
                    FirefoxOptions firefoxOptions = browserCapabilities.getFirefoxOptions();
                  //  firefoxOptions.setCapability("resolution", "1920X1080");
                    driver = new RemoteWebDriver(new URL(driverURL), firefoxOptions);
                    break;

                case (BrowserType.IE):
                    InternetExplorerOptions internetExplorerOptions = browserCapabilities.getInternetExplorerOptions();
//                    InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
//                    internetExplorerOptions.setCapability("resolution", "1920X1080");
                    driver = new RemoteWebDriver(new URL(driverURL), internetExplorerOptions);
                    break;

                case (BrowserType.EDGE):
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability("resolution", "1920X1080");
                    driver = new RemoteWebDriver(new URL(driverURL), edgeOptions);
                    break;
                default:
                    throw new AutomationException("browserName '" + browserName + "' is not supported");
            }
            return driver;
        }
    }
}

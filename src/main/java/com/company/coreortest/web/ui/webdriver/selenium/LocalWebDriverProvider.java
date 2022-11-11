package com.company.coreortest.web.ui.webdriver.selenium;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
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
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.company.coreortest.web.ui.exception.AutomationException;
import com.company.coreortest.web.ui.utils.ConfigRetriever;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalWebDriverProvider {
    private BrowserCapabilities browserCapabilities;

    public LocalWebDriverProvider(BrowserCapabilities browserCapabilities) {
        this.browserCapabilities = browserCapabilities;
    }
    /**
     * Creatyes local instance of web driver
     * Must set values for 'browserName' and 'driverLocation'
     * @return WebDriver with required capabilities or arguments
     */
    public WebDriver createLocalWebDriver() {
        String browserName = ConfigRetriever.getInstance().getProperty(CapabilityConstant.BROWSER_NAME);
        String driverLocation = ConfigRetriever.getInstance().getProperty(CapabilityConstant.DRIVER_LOCATION);
        if (CapabilityConstant.DRIVER_LOCATION == null || CapabilityConstant.DRIVER_LOCATION.isEmpty()) {
            throw new AutomationException("Driver Location property" + CapabilityConstant.DRIVER_LOCATION + " is not specified." +
                    " Ensure that it is specified in one of the following - system property, env variable or .properties file");
        }

        if (StringUtils.isBlank(CapabilityConstant.BROWSER_NAME)) {
            throw new AutomationException("Browser Name property " + CapabilityConstant.BROWSER_NAME + " is not set. " +
                    "Ensure that it is specified in one of the following - system property, env variable or .properties file");
        }
        WebDriver driver;

        switch (browserName) {
            case (BrowserType.CHROME):
                WebDriverManager.chromedriver().setup();
                //System.setProperty("webdriver.chrome.driver", driverLocation);
                ChromeOptions chromeOptions = browserCapabilities.getChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                break;

            case "chrome_headless":
                WebDriverManager.chromedriver().setup();
                //System.setProperty("webdriver.chrome.driver", driverLocation);
                ChromeOptions chromeOptionsHeadless = browserCapabilities.getChromeOptionsHeadless();
                driver = new ChromeDriver(chromeOptionsHeadless);
                break;

            case (BrowserType.FIREFOX):
               // System.setProperty("webdriver.gecko.driver", driverLocation);
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = browserCapabilities.getFirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case (BrowserType.IE):
                System.setProperty("webdriver.ie.driver", driverLocation);
                InternetExplorerOptions internetExplorerOptions = browserCapabilities.getInternetExplorerOptions();
                driver = new InternetExplorerDriver(internetExplorerOptions);
                break;

            case (BrowserType.EDGE):
                System.setProperty("webdriver.edge.driver", driverLocation);
                EdgeOptions edgeOptions = browserCapabilities.getEdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;

            case (BrowserType.SAFARI):
                //System.setProperty("webdriver.safari.driver", driverLocation);
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = browserCapabilities.getSafariOptions();
                driver = new SafariDriver(safariOptions);
                break;

            default:
                throw new AutomationException("browserName '" + browserName + "' is not supported");
        }
        return driver;
    }
}

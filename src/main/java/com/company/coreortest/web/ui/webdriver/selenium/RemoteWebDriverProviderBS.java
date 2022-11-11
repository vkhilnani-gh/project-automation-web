package com.company.coreortest.web.ui.webdriver.selenium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.company.coreortest.web.ui.exception.AutomationException;
import com.company.coreortest.web.ui.utils.ConfigRetriever;

public class RemoteWebDriverProviderBS {
    private BrowserCapabilities browserCapabilities;
    private String username = ConfigRetriever.getInstance().getProperty("BROWSERSTACK_USERNAME");
    private String accessKey = ConfigRetriever.getInstance().getProperty("BROWSERSTACK_ACCESS_KEY");

    public RemoteWebDriverProviderBS(BrowserCapabilities browserCapabilities) {
        this.browserCapabilities = browserCapabilities;
    }

    /**
     * Must set the value for "platform" (as browserStack) and value for 'browserName' in config.properties
     * or as environment variable/system property
     */
    public WebDriver createRemoteWebDriverBS() throws MalformedURLException {
        String browserName = ConfigRetriever.getInstance().getProperty(CapabilityConstant.BROWSER_NAME);
        RemoteWebDriver driver;
        Map<String, Object> capabilityMap = browserCapabilities.getCapabilityMapBS();
        Set<String> keys = capabilityMap.keySet();
        Iterator<String> itr;
        String key;
        String spec = "https://" + username + ":" + accessKey + "@hub.browserstack.com/wd/hub";

        switch (browserName) {
            case (BrowserType.EDGE):
                EdgeOptions edgeOptions = new EdgeOptions();
                itr = keys.iterator();
                while (itr.hasNext()) {
                    key = itr.next();
                    edgeOptions.setCapability(key, capabilityMap.get(key));
                }
                driver = new RemoteWebDriver(new URL(spec), edgeOptions);
                break;

            case (BrowserType.FIREFOX):
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                itr = keys.iterator();
                while (itr.hasNext()) {
                    key = itr.next();
                    firefoxOptions.setCapability(key, capabilityMap.get(key));
                }
                driver = new RemoteWebDriver(new URL(spec), firefoxOptions);
                break;

            case (BrowserType.IE):
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                itr = keys.iterator();
                while (itr.hasNext()) {
                    key = itr.next();
                    internetExplorerOptions.setCapability(key, capabilityMap.get(key));
                }
                driver = new RemoteWebDriver(new URL(spec), internetExplorerOptions);
                break;

            case (BrowserType.CHROME):
                ChromeOptions chromeOptions = new ChromeOptions();
                itr = keys.iterator();
                while (itr.hasNext()) {
                    key = itr.next();
                    chromeOptions.setCapability(key, capabilityMap.get(key));
                }
                driver = new RemoteWebDriver(new URL(spec), chromeOptions);
                break;

            default:
                throw new AutomationException("browser '" + CapabilityConstant.BROWSER_NAME + "' is not supported");
        }

    driver.setFileDetector(new LocalFileDetector());
    return driver;
    }
}

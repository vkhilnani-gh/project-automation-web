package com.company.coreortest.web.ui.webdriver.selenium;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariOptions;

import com.company.coreortest.web.ui.utils.ConfigRetriever;
import com.company.coreortest.web.ui.webdriver.selenium.BrowserCapabilities;

public class BrowserCapabilitiesBuilderDefault {
    private String operatingSystemBS = ConfigRetriever.getInstance().getProperty("osBS");
    private String osVersionBS = ConfigRetriever.getInstance().getProperty("osVersionBS");
    private String screenResolutionBS = ConfigRetriever.getInstance().getProperty("screenResolutionBS");
    private String browserStackLocal = ConfigRetriever.getInstance().getProperty("BROWSERSTACK_LOCAL");
    private String browserStackLocalIdentifier = ConfigRetriever.getInstance().getProperty("BROWSERSTACK_LOCAL_IDENTIFIER");

    private ChromeOptions chromeOptions;
    private ChromeOptions chromeOptionsHeadless;
    private InternetExplorerOptions internetExplorerOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;
    private SafariOptions safariOptions;

    //Browser Stack capabilities
    private Map<String, Object> capabilityMapBS;

    /**
     * Construct default (@link BrowserCapabilities).
     * @return (@link BrowserCapabilities).
     */
    public BrowserCapabilities buildCapabilities() {
        String platformToRun = ConfigRetriever.getInstance().getProperty(CapabilityConstant.PLATFORM);

        capabilityMapBS = new HashMap<>();
        capabilityMapBS.put("browser_version", "latest");
        capabilityMapBS.put("os", operatingSystemBS);
        capabilityMapBS.put("os_version", osVersionBS);
        capabilityMapBS.put("resolution", screenResolutionBS);
        //Optional
        capabilityMapBS.put("projectName", ConfigRetriever.getInstance().getProperty("projectName"));
        capabilityMapBS.put("buildName", ConfigRetriever.getInstance().getProperty("buildName"));

        //To support tunneling
        if (StringUtils.isNotBlank(browserStackLocal)) {
            capabilityMapBS.put("browserstack.local", browserStackLocal);
        }
        if (StringUtils.isNotBlank(browserStackLocalIdentifier)) {
            capabilityMapBS.put("browserstack.localIdentifier", browserStackLocalIdentifier);
        }


/*
        //Firefox arguments and capabilities
        argumentListFirefox = new ArrayList<>();
        argumentListFirefox.add("--ignore-certificate-errors");
        capabilitiesMapFirefox = new HashMap<>();
        capabilitiesMapFirefox.put("resolution", "1920X1080");
        capabilitiesMapFirefox.put(CapabilityType.ACCEPT_SSL_CERTS, true);
*/

        /**
         * Note that if browser options classes e.g. chromeOptions class is not initialised, it will throw an error due to code
         * Objects.requireNonNull(chromeOptions, "Valid chromeOptions for Chrome is required");
         */
        chromeOptions = new ChromeOptions();
        chromeOptionsHeadless = new ChromeOptions();
        edgeOptions = new EdgeOptions();
        internetExplorerOptions = new InternetExplorerOptions();
        firefoxOptions = new FirefoxOptions();
        safariOptions = new SafariOptions();

        if (StringUtils.isNotBlank(platformToRun) && platformToRun.contains(CapabilityConstant.REMOTE_DRIVER_DOCKER)) {
        //To fix ERR_CERT_AUTHORITY_INVALID issue in pipeline.
            chromeOptions.setAcceptInsecureCerts(true);
            chromeOptions.setCapability("resolution", "1920x1080");

            internetExplorerOptions.setCapability("resolution", "1920x1080");
            edgeOptions.setCapability("resolution", "1920x1080");
            firefoxOptions.setCapability("resolution", "1920x1080");
            safariOptions.setCapability("resolution", "1920x1080");
        } else {
            /**
             * Local Browser Settings
             */

            //Add Chrome options (Headless)
            chromeOptionsHeadless.addArguments("--headless");
            chromeOptionsHeadless.addArguments("--disable-gpu");
            chromeOptionsHeadless.addArguments("--ignore-certificate-errors");
            chromeOptionsHeadless.addArguments("--window-size=1920,1200");

            //Add IE Options

            //Add Chrome options

            //Add Firefox Options

            //Add Microsoft Edge Options
        }



        return new BrowserCapabilities.BrowserCapabilitiesBuilder()
                .addChromeOptions(chromeOptions)
                .addChromeOptionsHeadless(chromeOptionsHeadless)
                .addEdgeOptions(edgeOptions)
                .addFirefoxOptions(firefoxOptions)
                .addInternetExplorerOptions(internetExplorerOptions)
                .addSafariOptions(safariOptions)
                .addBrowserStackCapabilities(capabilityMapBS)
                .build();
    }
}

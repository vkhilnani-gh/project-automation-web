package com.company.coreortest.web.ui.webdriver.selenium;

/**
 * String Constants used for
 * 1)Looking up system properties/env variables/.properties data
 * 2)As Keys for DesiredCapability/BrowserOption, key-value pairs
 */
public class CapabilityConstant {
    private CapabilityConstant() {
        throw new IllegalStateException("String Constant class");
    }

    /**
     * @return string used for setting/reading in platform value. Platform value can be set to local, docker, browserStack etc
     */
    static final String PLATFORM = "platform";

    /**
     * @return string used for setting/reading in the Browser name. Browsers supported include chrome, chrome_headless, safari,
     * MicrosoftEdge.
     */
    static final String BROWSER_NAME = "browserName";

    /**
     * @return string used for setting/reading in the local path for browser driver (.exe)
     */
    static final String DRIVER_LOCATION = "driverLocation";


    /**
     * @return string used for defining/setting the platform value to Local
     */
    static final String LOCAL_DRIVER= "local";

    /**
     * @return string used for defining/setting the platform value to Local
     */
    static final String REMOTE_DRIVER_DOCKER= "docker";


    /**
     * @return string used for defining/setting the platform value to Browser Stack
     */
    static final String REMOTE_DRIVER_BROWSERSTACK = "browserStack";
}

package com.company.coreortest.web.ui.webdriver.selenium;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

/**
 * Capabilities or arguments to be passed to different browsers
 */
public class BrowserCapabilities {

    private ChromeOptions chromeOptions;
    private ChromeOptions chromeOptionsHeadless;
    private InternetExplorerOptions internetExplorerOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;
    private SafariOptions safariOptions;

    //Browser Stack capabilities
    private Map<String, Object> capabilityMapBS;

   /* //Local Web Driver Arguments
    private List<String> argumentListChrome;
    private List<String> argumentListChromeHeadless;
    private List<String> argumentListEdge;
    private List<String> argumentListFirefox;


    //Local Web Driver capabilities
    private Map<String, Object> capabilityMapChrome;
    private Map<String, Object> capabilityMapFirefox;
    private Map<String, Object> capabilityMapEdge;
    private Map<String, Object> capabilityMapIE;*/


    //private constructor to prevent making an instance of it
    private BrowserCapabilities(final BrowserCapabilitiesBuilder builder) {
/*        this.argumentListChrome = builder.argumentListChrome;
        this.argumentListChromeHeadless = builder.argumentListChromeHeadless;
        this.argumentListEdge = builder.argumentListEdge;
        this.argumentListFirefox = builder.argumentListFirefox;

        this.capabilityMapChrome = builder.capabilityMapChrome;
        this.capabilityMapFirefox = builder.capabilityMapFirefox;
        this.capabilityMapEdge = builder.capabilityMapEdge;
        this.capabilityMapIE = builder.capabilityMapIE;*/

        this.chromeOptions = builder.chromeOptions;
        this.chromeOptionsHeadless = builder.chromeOptionsHeadless;
        this.firefoxOptions = builder.firefoxOptions;
        this.internetExplorerOptions = builder.internetExplorerOptions;
        this.edgeOptions = builder.edgeOptions;
        this.safariOptions = builder.safariOptions;

        this.capabilityMapBS = builder.capabilityMapBS;
    }


    protected ChromeOptions getChromeOptions() {
        return chromeOptions;
    }

    public ChromeOptions getChromeOptionsHeadless() {
        return chromeOptionsHeadless;
    }

    public InternetExplorerOptions getInternetExplorerOptions() {
        return internetExplorerOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        return firefoxOptions;
    }

    public EdgeOptions getEdgeOptions() {
        return edgeOptions;
    }

    protected Map<String, Object> getCapabilityMapBS() {
        return capabilityMapBS;
    }

    public SafariOptions getSafariOptions() {
        return safariOptions;
    }
        /*//Override if you require addition of other capabilities.//TO-D0: Understand usage.
    protected Map<String, Object> addOtherCapabilities() {
        return Collections.emptyMap();
    }

    protected Map<String, Object> getCapabilityMapChrome() {
        return capabilityMapChrome;
    }

    protected Map<String, Object> getCapabilityMapFireFox() {
        return capabilityMapFirefox;
    }

    protected Map<String, Object> getCapabilityMapEdge() {
        return capabilityMapEdge;
    }

    protected Map<String, Object> getCapabilityMapIE() {
        return capabilityMapIE;
    }

    protected List<String> getArgumentListChrome() {
        return argumentListChrome;
    }

    protected List<String> getArgumentListChromeHeadless() {
        return argumentListChromeHeadless;
    }

    protected List<String> getArgumentListEdge() {
        return argumentListEdge;
    }

    protected List<String> getArgumentListFirefox() {
        return argumentListFirefox;
    }

    }*/

        /**
         * Builder for BrowserCapabilities. The methods in it will allow you to pass arguments or capabilities to browser.
         */
        public static class BrowserCapabilitiesBuilder {

            private ChromeOptions chromeOptions;
            private ChromeOptions chromeOptionsHeadless;
            private InternetExplorerOptions internetExplorerOptions;
            private FirefoxOptions firefoxOptions;
            private EdgeOptions edgeOptions;
            private SafariOptions safariOptions;

/*        //Local Web Driver Arguments
        private List<String> argumentListChrome;
        private List<String> argumentListChromeHeadless;
        private List<String> argumentListEdge;
        private List<String> argumentListFirefox;

        private Map<String, Object> capabilityMapChrome;
        private Map<String, Object> capabilityMapFirefox;
        private Map<String, Object> capabilityMapEdge;
        private Map<String, Object> capabilityMapIE;*/


            //Browser Stack capabilities
            private Map<String, Object> capabilityMapBS;

            /**
             * This method will allow you to pass ChromeOptions to Chrome Browser
             * @param chromeOptions to configure or make changes to Chrome Browser
             * @return
             */
            public BrowserCapabilitiesBuilder addChromeOptions(ChromeOptions chromeOptions) {
                Objects.requireNonNull(chromeOptions, "Valid ChromeOptions for Chrome is required." +
                        "Ensure that the instance of ChromeOptions class is initiated");
                this.chromeOptions = chromeOptions;
                return this;
            }

            public BrowserCapabilitiesBuilder addChromeOptionsHeadless(ChromeOptions chromeOptionsHeadless) {
                Objects.requireNonNull(chromeOptions, "Valid ChromeOptions for Chrome required." +
                        "Ensure that the instance of ChromeOptions class is initiated for headless mode");
                this.chromeOptionsHeadless = chromeOptionsHeadless;
                return this;
            }

            public BrowserCapabilitiesBuilder addInternetExplorerOptions(InternetExplorerOptions internetExplorerOptions) {
                Objects.requireNonNull(internetExplorerOptions, "Valid InternetExplorer Options for IE required." +
                        "Ensure that the instance of InternetExplorerOptions class is initiated");
                this.internetExplorerOptions = internetExplorerOptions;
                return this;
            }

            public BrowserCapabilitiesBuilder addEdgeOptions(EdgeOptions edgeOptions) {
                Objects.requireNonNull(edgeOptions, "Valid EdgeOptions for Edge required." +
                        "Ensure that the instance of EdgeOptions class is initiated");
                this.edgeOptions = edgeOptions;
                return this;
            }

            public BrowserCapabilitiesBuilder addFirefoxOptions(FirefoxOptions firefoxOptions) {
                Objects.requireNonNull(firefoxOptions, "Valid FirefoxOptions for Firefox required." +
                        "Ensure that the instance of FirefoxOptions class is initiated");
                this.firefoxOptions = firefoxOptions;
                return this;
            }

            public BrowserCapabilitiesBuilder addSafariOptions(SafariOptions safariOptions) {
                Objects.requireNonNull(safariOptions, "Valid SafariOptions for Safari required." +
                        "Ensure that the instance of SafariOptions class is initiated");
                this.safariOptions = safariOptions;
                return this;
            }

        /*public BrowserCapabilitiesBuilder addBrowserArgumentsChrome(final List<String> argumentListChrome) {
            Objects.requireNonNull(argumentListEdge, "Valid argument list for Chrome is required");
            this.argumentListChrome = argumentListChrome;
            return this;
        }

        public BrowserCapabilitiesBuilder addBrowserArgumentsChromeHeadless(final List<String> argumentListChromeHeadless) {
            Objects.requireNonNull(argumentListChromeHeadless, "Valid argument list for Chrome in " +
                    "Headless mode is required");
            this.argumentListChromeHeadless = argumentListChromeHeadless;
            return this;
        }

        public BrowserCapabilitiesBuilder addBrowserArgumentsEdge(final List<String> argumentListEdge) {
            Objects.requireNonNull(argumentListEdge, "Valid argument list for Edge is required");
            this.argumentListEdge = argumentListEdge;
            return this;
        }
        public BrowserCapabilitiesBuilder addBrowserArgumentsFirefox(final List<String> argumentListFirefox) {
            Objects.requireNonNull(argumentListFirefox, "Valid argument list for Firefox is required");
            this.argumentListFirefox = argumentListFirefox;
            return this;
        }

        public BrowserCapabilitiesBuilder addBrowserCapabilitiesFirefox(final Map<String, Object> capabilityMapFirefox) {
            Objects.requireNonNull(capabilityMapFirefox, "Valid capability map for Firefox is required");
            this.capabilityMapFirefox = capabilityMapFirefox;
            return this;
        }

        public BrowserCapabilitiesBuilder addBrowserCapabilitiesChrome(final Map<String, Object> capabilityMapChrome) {
            Objects.requireNonNull(capabilityMapChrome, "Valid capability map for Chrome is required");
            this.capabilityMapChrome = capabilityMapChrome;
            return this;
        }

        public BrowserCapabilitiesBuilder addBrowserCapabilitiesEdge(final Map<String, Object> capabilityMapEdge) {
            Objects.requireNonNull(capabilityMapEdge, "Valid capability map for Edge is required");
            this.capabilityMapEdge = capabilityMapEdge;
            return this;
        }

        public BrowserCapabilitiesBuilder addBrowserCapabilitiesIE(final Map<String, Object> capabilityMapIE) {
            Objects.requireNonNull(capabilityMapIE, "Valid capability map for IE is required");
            this.capabilityMapIE = capabilityMapIE;
            return this;
        }*/

            public BrowserCapabilitiesBuilder addBrowserStackCapabilities(final Map<String, Object> capabilityMapBS) {
                Objects.requireNonNull(capabilityMapBS, "Valid capability map for Browser Stack is required");
                this.capabilityMapBS = capabilityMapBS;
                return this;
            }

            /**
             *
             * @return the finally constructed BrowserCapabilities object
             */
            public BrowserCapabilities build() {
                return new BrowserCapabilities(this);
            }
        }
    }

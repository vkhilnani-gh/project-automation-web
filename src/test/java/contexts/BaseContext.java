package contexts;

import org.openqa.selenium.WebDriver;

import com.company.coreortest.web.ui.webdriver.selenium.BrowserCapabilitiesBuilderDefault;
import com.company.coreortest.web.ui.webdriver.selenium.WebDriverFactory;
import com.company.coreortest.web.ui.webdriver.selenium.WebDriverFactoryDefault;

/**
 * To share the Test Context / Scenario Context / Test State with all the Step Definitions file.
 * This simply tells a Dependency Injection container (PicoContainer) to instantiate your step definition classes and wire them up correctly.
 * Cucumber scans your classes with step definitions in them, passes them to PicoContainer,
 * then asks it to create new instances for every scenario.
 * Q: Thus, State is shared between steps of a scenario and not between multiple scenarios with/without parallel run?
 */
public class BaseContext {

    //WebDriver
    public WebDriver driver;

    /**
     * Method 2: Below way of initialising can avoid creating an instance of page/action classes in every step
     */
    private WebDriverFactory webDriverFactory = new WebDriverFactoryDefault();
    public BaseContext() {
        driver = webDriverFactory
                .getWebDriver(new BrowserCapabilitiesBuilderDefault().buildCapabilities());
    }
}

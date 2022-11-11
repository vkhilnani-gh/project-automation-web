package definitions.hooks;


import java.time.Duration;

import com.company.coreortest.web.ui.webdriver.selenium.BrowserCapabilities;
import com.company.coreortest.web.ui.webdriver.selenium.BrowserCapabilitiesBuilderDefault;
import com.company.coreortest.web.ui.webdriver.selenium.WebDriverFactory;
import com.company.coreortest.web.ui.webdriver.selenium.WebDriverFactoryDefault;
import com.company.coreortest.web.ui.webdriver.selenium.utils.ImageCapture.ImageCaptureUtils;

import contexts.BaseContext;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;

/**
 * Cucumber supports hooks, which are blocks of code that run before or after each scenario.
 * An important thing to note about the after hook is that even in case of test fail, after hook will execute for sure.
 */
public class BaseHooks implements En {

    public BaseHooks(BaseContext baseContext) {

        /**
         * There are 2 ways to initialise the driver.
         * Method 1: Initialise driver in BaseHooks.
         * This will require creating a new instance of page/action class in every step.
         * Method 2: Initialise driver in BaseContext.
         * This can avoid creating an instance of page/action classes in every step
         */
        Before (0, () -> {
           // Method 1
//            WebDriverFactory webDriverFactory = new WebDriverFactoryDefault();
//            baseContext.driver = webDriverFactory
//                    .getWebDriver(new BrowserCapabilitiesBuilderDefault().buildCapabilities());
            baseContext.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            baseContext.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            baseContext.driver.manage().window().maximize();


        });

        After(0, () -> {
            baseContext.driver.quit();
            });

        After(1, (Scenario scenario) -> {
            if (scenario.isFailed()) {
                ImageCaptureUtils imageCaptureUtils = new ImageCaptureUtils(baseContext.driver);
                String imageName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "");
                imageCaptureUtils.captureScreenshot(imageName, ".//build/reports/app/screenshot");
            }
        });
    }
}

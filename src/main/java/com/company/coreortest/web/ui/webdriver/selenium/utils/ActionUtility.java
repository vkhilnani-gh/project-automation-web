package com.company.coreortest.web.ui.webdriver.selenium.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.company.coreortest.web.ui.enums.ScrollDirections;
import com.company.coreortest.web.ui.exception.AutomationException;

/**
 * Utility class for handling keyboard and mouse events
 */
public class ActionUtility {
    private WebDriver driver;

    public ActionUtility(WebDriver driver) {
        if (driver == null) {
            throw new AutomationException("WebDriver cannot be null");
        }
        this.driver = driver;
    }

    /**
     * Method to hover over the Web element. This does not click on the element.
     * @param element - the Web element on which the action is performed
     * @return element - the Web element on which the action is performed.
     */
    public WebElement hoverOnElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        return element;
    }

    /**
     * Scroll in the indicated scroll direction and mentioned pixels.
     * @param scrollDirection as enum ScrollDirections
     * @param pixels e.g 250
     */
    public void scroll (ScrollDirections scrollDirection, int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        switch (scrollDirection) {
            case UP:
                js.executeScript("window.scrollBy(0,-" + pixels + ")");
                break;
            case DOWN:
                js.executeScript("window.scrollBy(0," + pixels + ")");
                break;
            case LEFT:
                js.executeScript("window.scrollBy(-" + pixels + ",0)");
                break;
            case RIGHT:
                js.executeScript("window.scrollBy(" + pixels + ",0)");
                break;
            default:
                throw new AutomationException("ScrollDirection '" + scrollDirection + "' is not supported");
        }
    }

    /**
     * Scroll to the element till it comes into view.
     * @param element - WebElement on which the scroll action is performed.
     * @return WebElement - the WebElement passed in the argument.
     */
    public WebElement scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    public void dragAndDropElementJS(WebElement fromElement, WebElement toElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("function createEvent(typeOfEvent) {\n" +"var event =document.createEvent(\"CustomEvent\");\n"
                +"event.initCustomEvent(typeOfEvent,true, true, null);\n" +"event.dataTransfer = {\n" +"data: {},\n"
                +"setData: function (key, value) {\n" +"this.data[key] = value;\n" +"},\n"
                +"getData: function (key) {\n" +"return this.data[key];\n" +"}\n" +"};\n" +"return event;\n"
                +"}\n" +"\n" +"function dispatchEvent(element, event,transferData) {\n"
                +"if (transferData !== undefined) {\n" +"event.dataTransfer = transferData;\n" +"}\n"
                +"if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n" +"} else if (element.fireEvent) {\n"
                +"element.fireEvent(\"on\" + event.type, event);\n" +"}\n" +"}\n" +"\n"
                +"function simulateHTML5DragAndDrop(element, destination) {\n"
                +"var dragStartEvent =createEvent('dragstart');\n" +"dispatchEvent(element, dragStartEvent);\n"
                +"var dropEvent = createEvent('drop');\n"
                +"dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
                +"var dragEndEvent = createEvent('dragend');\n"
                +"dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n"
                +"}\n" +"\n" +"var source = arguments[0];\n" +"var destination = arguments[1];\n"
                +"simulateHTML5DragAndDrop(source,destination);", fromElement, toElement);
    }

    /**
     * Click an element. Includes elementToBeClickable check and 3 re-tries.
     * @param element WebElement on which the click action is performed.
     * @return WebElement passed in the argument.
     */
    public WebElement clickElement(WebElement element) {
        WebDriverWaitUtility webDriverWaitUtility = new WebDriverWaitUtility(driver);
        WebElementMisc loc = new WebElementMisc(driver);
        int attempts = 0;
        while (attempts < 3) {
            try {
                webDriverWaitUtility.waitUntil(ExpectedConditions.elementToBeClickable(element), 5);
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                element = loc.refreshElementLocator(element);
                attempts++;
            }
        }
        return element;
    }
}

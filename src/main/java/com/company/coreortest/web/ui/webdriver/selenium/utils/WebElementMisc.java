package com.company.coreortest.web.ui.webdriver.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementMisc {
    private WebDriver webDriver;

    public  WebElementMisc(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement refreshElementLocator(WebElement element) {
        return webDriver.findElement(getByFromElement(element));
    }

    public By getByFromElement(WebElement element) {
        By by;
        String[] pathVariables = (element.toString().split("->")[1].replaceFirst("(?s)(.*)]", "$1" + ""))
                .split(":");

        String selector = pathVariables[0].trim();
        String value = pathVariables[1].trim();

        switch(selector) {
            case "id":
                by = By.id(value);
                break;
            case "className":
                by = By.className(value);
                break;
            case "tagName":
                by = By.tagName(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "cssSelector":
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            default:
                throw new IllegalStateException("locator : " + selector + " not found!");
        }
        return by;
    }
}
